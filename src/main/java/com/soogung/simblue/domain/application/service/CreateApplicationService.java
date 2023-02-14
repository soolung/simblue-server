package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.repository.AnswerRepository;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.presentation.dto.request.QuestionRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationRequest;
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
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final OwnerRepository ownerRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(CreateApplicationRequest request) {
        Application application = applicationRepository.save(request.toEntity());
        saveApplicationOwner(request.getApplicationOwnerIdList(), application);

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
