package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelApplicationRequestService {

    private final ApplicationFacade applicationFacade;
    private final ApplicationRequestBlockRepository applicationRequestBlockRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long id) {
        applicationRequestBlockRepository.deleteApplicationRequestBlockByApplicationAndStudent(
                applicationFacade.findApplicationById(id), userFacade.findStudentByUser(userFacade.getCurrentUser()));
    }
}
