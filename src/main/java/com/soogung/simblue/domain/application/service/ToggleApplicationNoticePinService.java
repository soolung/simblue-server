package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.facade.ApplicationNoticeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ToggleApplicationNoticePinService {

    private final ApplicationNoticeFacade applicationNoticeFacade;

    @Transactional
    public void execute(Long id) {
        applicationNoticeFacade.findApplicationNoticeById(id).toggleIsPinned();
    }
}
