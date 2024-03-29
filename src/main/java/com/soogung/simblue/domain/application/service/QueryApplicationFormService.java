package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationFormResponse;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryApplicationFormService {

    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;
    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationFormResponse execute(User user, Long id) {
        Application application = applicationFacade.findApplicationById(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, user.getId());

        return ApplicationFormResponse.of(
                application,
                !replyBlockRepository.existsByApplication(application),
                ownerRepository.findOwnerByApplicationWithoutUser(application, user)
        );
    }
}
