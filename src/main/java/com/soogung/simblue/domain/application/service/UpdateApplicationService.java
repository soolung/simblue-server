package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.repository.AnswerRepository;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.domain.type.Status;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.QuestionRequest;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateApplicationService {

    private final UserFacade userFacade;
    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public void execute(Long id, ApplicationRequest request) {
        Teacher teacher = userFacade.findTeacherByUser(userFacade.getCurrentUser());
        Application application = applicationFacade.getSimpleApplication(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, teacher.getId());

        application.updateInformation(
                request.getTitle(),
                request.getDescription(),
                request.getStartDate(),
                request.getEndDate(),
                request.getEmoji(),
                request.getAllowsDuplication(),
                request.getAllowsUpdatingReply(),
                request.getIsAlways() ? Status.ALWAYS : Status.OPENED
        );

        ownerRepository.deleteByApplication(application);
        ownerRepository.flush();
        questionRepository.deleteByApplication(application);
        questionRepository.flush();

        saveApplicationOwner(request.getOwnerList(), application);
        request.getQuestionList()
                .forEach(q -> saveApplicationAnswer(q, application));
    }

    private void saveApplicationOwner(List<Long> ownerIdList, Application application) {
        ownerRepository.saveAll(
                ownerIdList.stream().map(
                        (id) -> Owner.builder()
                                .teacher(userFacade.findTeacherById(id))
                                .application(application)
                                .build()
                ).collect(Collectors.toList())
        );
    }

    private void saveApplicationAnswer(QuestionRequest request, Application application) {
        Question question = questionRepository.save(request.toEntity(application));
        if (request.getType().isHasAnswer() && request.getAnswerList() != null) {
            answerRepository.saveAll(
                    request.getAnswerList().stream()
                            .map(a -> a.toEntity(question))
                            .collect(Collectors.toList())
            );
        }
    }

}
