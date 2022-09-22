package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.service.CreateApplicationNoticeService;
import com.soogung.simblue.domain.application.service.UpdateApplicationNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application/notice")
@RequiredArgsConstructor
public class ApplicationNoticeController {

    private final CreateApplicationNoticeService createApplicationNoticeService;
    private final UpdateApplicationNoticeService updateApplicationNoticeService;

    @PostMapping
    public void createApplicationNotice(@RequestBody @Valid CreateApplicationNoticeRequest request) {
        createApplicationNoticeService.execute(request);
    }

    @PutMapping("/{id}")
    public void updateApplicationNotice(
            @PathVariable Long id,
            @RequestBody @Valid UpdateApplicationNoticeRequest request
    ) {
        updateApplicationNoticeService.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteApplicationNotice(@PathVariable Long id) {

    }

    @PutMapping("/{id}/pinned")
    public void toggleApplicationNoticePin(@PathVariable Long id) {

    }
}
