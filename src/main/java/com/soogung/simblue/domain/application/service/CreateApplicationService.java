package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationOwner;
import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.repository.ApplicationAnswerRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationOwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationQuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationQuestionRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationRequest;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationQuestionRepository applicationQuestionRepository;
    private final ApplicationAnswerRepository applicationAnswerRepository;
    private final ApplicationOwnerRepository applicationOwnerRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(CreateApplicationRequest request) {
        Application application = applicationRepository.save(request.toEntity());
        saveApplicationOwner(request.getApplicationOwnerIdList(), application);

        request.getQuestionList()
                .forEach(q -> saveApplicationAnswer(q, application));
    }

    private void saveApplicationOwner(List<Long> applicationOwnerIdList, Application application) {
        applicationOwnerRepository.saveAll(
                applicationOwnerIdList.stream().map(
                        (id) -> ApplicationOwner.builder()
                                .teacher(userFacade.findTeacherById(id))
                                .application(application)
                                .build()
                ).collect(Collectors.toList())
        );
    }

    private void saveApplicationAnswer(ApplicationQuestionRequest request, Application application) {
        ApplicationQuestion question = applicationQuestionRepository.save(request.toEntity(application));
        if (request.getType().isHasAnswer() && request.getAnswerList() != null) {
            applicationAnswerRepository.saveAll(
                    request.getAnswerList().stream()
                    .map(a -> a.toEntity(question))
                    .collect(Collectors.toList())
            );
        }
    }
}
