package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationRequest;
import com.soogung.simblue.domain.application.service.CreateApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final CreateApplicationService applicationService;

    @PostMapping
    public void createApplication(CreateApplicationRequest request) {
        applicationService.execute(request);
    }
}
