package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.repository.AnswerRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequest;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloseApplicationService {

    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;
    private final UserFacade userFacade;

    public void execute(Long id) {
        Teacher teacher = userFacade.getCurrentTeacher();
        Application application = applicationFacade.findApplicationById(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, teacher.getId());

        application.close();
    }
}
