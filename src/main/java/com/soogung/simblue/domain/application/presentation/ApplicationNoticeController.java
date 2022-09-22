package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.service.CreateApplicationNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application/notice")
@RequiredArgsConstructor
public class ApplicationNoticeController {

    private final CreateApplicationNoticeService createApplicationNoticeService;

    @PostMapping
    public void createApplicationNotice(@RequestBody @Valid CreateApplicationNoticeRequest request) {
        createApplicationNoticeService.execute(request);
    }

    @PutMapping("/{noticeId}")
    public void updateApplicationNotice(
            @PathVariable Long noticeId,
            @RequestBody @Valid UpdateApplicationNoticeRequest request
    ) {

    }

    @DeleteMapping("/{noticeId}")
    public void deleteApplicationNotice(@PathVariable Long noticeId) {

    }

    @PutMapping("/{noticeId}/pinned")
    public void toggleApplicationNoticePin(@PathVariable Long noticeId) {

    }
}
