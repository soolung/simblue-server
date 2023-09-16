package com.soogung.simblue.domain.notice.service;

import com.soogung.simblue.domain.notice.facade.NoticeFacade;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ToggleNoticePinService {

    private final NoticeFacade noticeFacade;

    @Transactional
    public void execute(User user, Long id) {
        // TODO :: validate permission
        noticeFacade.findApplicationNoticeById(id).toggleIsPinned();
    }
}