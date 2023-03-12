package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationListResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryPagingApplication {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public ApplicationListResponse execute(Pageable pageable) {
        return new ApplicationListResponse(
                applicationRepository.findAllByOrderByIdDesc(pageable)
                        .stream().map(ApplicationResponse::of).collect(Collectors.toList())
        );
    }
}
