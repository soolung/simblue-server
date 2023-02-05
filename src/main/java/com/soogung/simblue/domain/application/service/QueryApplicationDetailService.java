package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryApplicationDetailService {

    private final ApplicationFacade applicationFacade;
    private final NoticeRepository noticeRepository;

    public ApplicationDetailResponse execute(Long id) {

        List<NoticeResponse> noticeList = noticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(id)
                .stream().map(NoticeResponse::of).collect(Collectors.toList());

        return ApplicationDetailResponse.of(
                applicationFacade.findApplicationById(id), noticeList);
    }
}
