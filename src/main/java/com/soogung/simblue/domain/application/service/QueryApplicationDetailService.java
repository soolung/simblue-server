package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryApplicationDetailService {

    private final ApplicationFacade applicationFacade;
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public ApplicationDetailResponse execute(Long id) {
        Application application = applicationFacade.findApplicationById(id);
        application.validateStatus();

        List<NoticeResponse> noticeList = noticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(id)
                .stream().map(NoticeResponse::of).collect(Collectors.toList());

        return ApplicationDetailResponse.of(application, noticeList);
    }
}
