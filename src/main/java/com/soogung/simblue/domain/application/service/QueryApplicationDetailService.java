package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationNoticeRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationNoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryApplicationDetailService {

    private final ApplicationFacade applicationFacade;
    private final ApplicationNoticeRepository applicationNoticeRepository;

    public ApplicationDetailResponse execute(Long id) {

        List<ApplicationNoticeResponse> noticeList = applicationNoticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(id)
                .stream().map(ApplicationNoticeResponse::of).collect(Collectors.toList());

        return ApplicationDetailResponse.of(
                applicationFacade.findApplicationById(id), noticeList);
    }
}
