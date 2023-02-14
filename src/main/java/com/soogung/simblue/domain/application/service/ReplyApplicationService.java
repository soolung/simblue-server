package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.application.exception.AlreadyReplyException;
import com.soogung.simblue.domain.application.exception.ApplicationHasAlreadyEndedException;
import com.soogung.simblue.domain.application.exception.ApplicationHasNotStartedYetException;
import com.soogung.simblue.domain.application.exception.QuestionIsRequiredException;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyRequest;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyApplicationService {

    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;
    private final ReplyBlockRepository replyBlockRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void execute(ReplyBlockRequest request) {
        Application application = applicationFacade.findApplicationById(request.getApplicationId());
        Student student = userFacade.findStudentByUser(userFacade.getCurrentUser());
        validateApplicationPeriod(application);
        validateFirstResponse(application, student);

        ReplyBlock block = replyBlockRepository.save(createReplyBlock(application, student));
        replyRepository.saveAll(
                toApplicationRequestsFromRequest(
                        request.getRequestRequestList(), block));
    }

    private void validateApplicationPeriod(Application application) {
        if (application.getIsAlways()) {
            return;
        }

        if (LocalDate.now().isBefore(application.getStartDate())) {
            throw ApplicationHasNotStartedYetException.EXCEPTION;
        }

        if (LocalDate.now().isAfter(application.getEndDate())) {
            throw ApplicationHasAlreadyEndedException.EXCEPTION;
        }
    }

    private void validateFirstResponse(Application application, Student student) {
        if (!application.getAllowsDuplication() && replyBlockRepository.existsByApplicationAndStudent(application, student)) {
            throw AlreadyReplyException.EXCEPTION;
        }
    }

    private ReplyBlock createReplyBlock(Application application, Student student) {
        return ReplyBlock.builder()
                .application(application)
                .student(student)
                .build();
    }

    private List<Reply> toApplicationRequestsFromRequest(
            List<ReplyRequest> replyRequestList,
            ReplyBlock block
    ) {
        return replyRequestList.stream()
                .map(r -> {
                    Question q = applicationFacade.findQuestionById(r.getId());
                    validateRequiredQuestion(r, q);
                    return r.getReplyList().stream()
                            .map(a -> createReplyFrom(r, block, q, a))
                            .collect(Collectors.toList());
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Reply createReplyFrom(
            ReplyRequest request,
            ReplyBlock block,
            Question question,
            String answer
    ) {
        validateRequiredQuestion(question, answer);
        return request.toEntity(question, block, answer);
    }

    private void validateRequiredQuestion(ReplyRequest request, Question question) {
        if (question.getIsRequired() && request.getReplyList().isEmpty()) {
            throw QuestionIsRequiredException.EXCEPTION;
        }
    }

    private void validateRequiredQuestion(
            Question question,
            String answer
    ) {
        if (question.getIsRequired() && (answer == null || answer.equals(""))) {
            throw QuestionIsRequiredException.EXCEPTION;
        }
    }
}
