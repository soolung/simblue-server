package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteApplicationService {

    private final UserFacade userFacade;
    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void execute(Long id) {
        Teacher teacher = userFacade.findTeacherByUser(userFacade.getCurrentUser());
        Application application = applicationFacade.findApplicationById(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, teacher.getId());

        application.delete();
    }
}
