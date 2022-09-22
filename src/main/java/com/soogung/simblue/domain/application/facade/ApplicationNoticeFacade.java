package com.soogung.simblue.domain.application.facade;

import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import com.soogung.simblue.domain.application.domain.repository.ApplicationNoticeRepository;
import com.soogung.simblue.domain.application.exception.ApplicationNoticeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplicationNoticeFacade {

    private final ApplicationNoticeRepository applicationNoticeRepository;

    @Transactional(readOnly = true)
    public ApplicationNotice findApplicationNoticeById(Long id) {
        return applicationNoticeRepository.findById(id)
                .orElseThrow(() -> ApplicationNoticeNotFoundException.EXCEPTION);
    }
}
