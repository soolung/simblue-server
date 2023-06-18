package com.soogung.simblue.domain.notice.service;

import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNoticeService {

    private final NoticeRepository noticeRepository;
    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(CreateNoticeRequest request) {
        noticeRepository.save(
                request.toEntity(
                        applicationFacade.findApplicationById(request.getApplicationId()),
                        userFacade.getCurrentUser()
                )
        );
    }
}
