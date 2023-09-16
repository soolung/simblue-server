package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CloseApplicationService {

    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void execute(User user, Long id) {
        Application application = applicationFacade.getSimpleApplication(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, user.getId());

        application.close();
    }
}
