package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import com.soogung.simblue.domain.application.domain.repository.ApplicationNoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.facade.ApplicationNoticeFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateApplicationNoticeService {

    private final ApplicationNoticeRepository applicationNoticeRepository;
    private final ApplicationNoticeFacade applicationNoticeFacade;
    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long id, UpdateApplicationNoticeRequest request) {
        ApplicationNotice notice = applicationNoticeFacade.findApplicationNoticeById(id);
        notice.updateNotice(request.getNotice());
    }
}
