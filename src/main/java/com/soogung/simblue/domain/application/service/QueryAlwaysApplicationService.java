package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAlwaysApplicationService {

    private final ApplicationRepository applicationRepository;

    public List<ApplicationResponse> execute() {
        return applicationRepository.findAllByIsAlways(true).stream()
                .map(ApplicationResponse::of).collect(Collectors.toList());
    }
}
