package com.soogung.simblue.domain.notice.service;

import com.soogung.simblue.domain.notice.facade.NoticeFacade;
import com.soogung.simblue.domain.notice.presentation.dto.request.UpdateNoticeRequest;
import com.soogung.simblue.domain.notice.domain.Notice;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNoticeService {

    private final NoticeFacade noticeFacade;

    @Transactional
    public void execute(User user, Long id, UpdateNoticeRequest request) {
        // TODO :: validate permission
        Notice notice = noticeFacade.findApplicationNoticeById(id);
        notice.updateNotice(request.getNotice());
    }
}
