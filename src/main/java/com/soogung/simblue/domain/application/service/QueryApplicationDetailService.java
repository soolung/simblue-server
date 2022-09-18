package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryApplicationDetailService {

    private final ApplicationFacade applicationFacade;

    public ApplicationDetailResponse execute(Long id) {
        return ApplicationDetailResponse.of(
                applicationFacade.findApplicationById(id));
    }
}
