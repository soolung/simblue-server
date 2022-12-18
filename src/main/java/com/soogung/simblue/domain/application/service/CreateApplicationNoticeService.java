package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationNoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationNoticeRequest;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateApplicationNoticeService {

    private final ApplicationNoticeRepository applicationNoticeRepository;
    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(CreateApplicationNoticeRequest request) {
        applicationNoticeRepository.save(
                request.toEntity(
                        applicationFacade.findApplicationById(request.getApplicationId()),
                        userFacade.findTeacherByUser(userFacade.getCurrentUser())
                )
        );
    }
}
