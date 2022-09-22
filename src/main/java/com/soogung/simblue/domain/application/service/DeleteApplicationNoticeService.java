package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationNoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationNoticeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteApplicationNoticeService {

    private final ApplicationNoticeRepository applicationNoticeRepository;
    private final ApplicationNoticeFacade applicationNoticeFacade;

    public void execute(Long id) {
        applicationNoticeRepository.delete(
                applicationNoticeFacade.findApplicationNoticeById(id));
    }
}
