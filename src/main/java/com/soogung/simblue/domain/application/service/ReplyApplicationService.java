package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.application.exception.AlreadyReplyException;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyRequest;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
        Student student = userFacade.getCurrentStudent();
        application.validateStatus();
        application.validatePeriod();
        validateFirstResponse(application, student);

        ReplyBlock block = replyBlockRepository.save(createReplyBlock(application, student));
        replyRepository.saveAll(
                toReplyFrom(request.getReplyList(), block));
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

    private List<Reply> toReplyFrom(
            List<ReplyRequest> replyRequestList,
            ReplyBlock block
    ) {
        return replyRequestList.stream()
                .map(r -> {
                    Question q = applicationFacade.findQuestionById(r.getId());
                    q.validateAnswer(r);
                    return r.getReplyDetailList().stream()
                            .map(a -> createReplyFrom(r, block, q, a))
                            .filter(Objects::nonNull)
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
        question.validateAnswer(answer);

        if (Objects.isNull(answer) || answer.equals("")) {
            return null;
        }

        Reply reply = request.toEntity(question, block, answer);
        reply.putReplyBlock(block);
        return reply;
    }
}
