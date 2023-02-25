package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationFormResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryApplicationCreationFormService {

    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public ApplicationFormResponse execute(Long id) {
        Application application = applicationFacade.findApplicationById(id);

        return ApplicationFormResponse.of(
                application,
                ownerRepository.findAllByApplication(application)
        );
    }
}
