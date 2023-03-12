package com.soogung.simblue.domain.notice.facade;

import com.soogung.simblue.domain.notice.domain.Notice;
import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.notice.exception.NoticeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NoticeFacade {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public Notice findApplicationNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
    }
}
