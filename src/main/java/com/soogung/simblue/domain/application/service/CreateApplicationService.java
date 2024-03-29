package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.repository.AnswerRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.OwnerRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.QuestionRequest;
import com.soogung.simblue.domain.user.domain.User;
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
    public void execute(User user, ApplicationRequest request) {
        Application application = applicationRepository.save(request.toEntity());

        saveApplicationOwner(request.getOwnerList(), application, user);

        request.getQuestionList()
                .forEach(q -> saveApplicationAnswer(q, application));
    }

    private void saveApplicationOwner(
            List<OwnerRequest> ownerRequestList, Application application, User user) {
        ownerRepository.save(Owner.builder()
                .user(user)
                .application(application)
                .build());

        ownerRepository.saveAll(
                ownerRequestList.stream().map(
                        (owner) -> Owner.builder()
                                .user(userFacade.getUserById(owner.getUserId()))
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
