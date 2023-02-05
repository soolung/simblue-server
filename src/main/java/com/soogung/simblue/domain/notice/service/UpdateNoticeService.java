package com.soogung.simblue.domain.notice.service;

import com.soogung.simblue.domain.notice.facade.NoticeFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import com.soogung.simblue.domain.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNoticeService {

    private final NoticeFacade noticeFacade;

    @Transactional
    public void execute(Long id, UpdateApplicationNoticeRequest request) {
        Notice notice = noticeFacade.findApplicationNoticeById(id);
        notice.updateNotice(request.getNotice());
    }
}
