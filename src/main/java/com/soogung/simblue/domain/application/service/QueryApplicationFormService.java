package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationFormResponse;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryApplicationFormService {

    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;
    private final OwnerRepository ownerRepository;
    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationFormResponse execute(Long id) {
        Application application = applicationFacade.findApplicationById(id);
        Teacher teacher = userFacade.findTeacherByUser(userFacade.getCurrentUser());
        application.validateStatus();
        application.validatePermission(ownerRepository, teacher.getId());

        return ApplicationFormResponse.of(
                application,
                !replyBlockRepository.existsByApplication(application),
                ownerRepository.findOwnerByApplicationWithoutTeacher(application, teacher)
        );
    }
}
