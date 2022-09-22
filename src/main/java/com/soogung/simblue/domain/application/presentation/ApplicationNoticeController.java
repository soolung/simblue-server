package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationNoticeRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application/{applicationId}/notice")
@RequiredArgsConstructor
public class ApplicationNoticeController {

    @PostMapping
    public void createApplicationNotice(
            @PathVariable Long applicationId,
            @RequestBody @Valid CreateApplicationNoticeRequest request
    ) {

    }

    @PutMapping("/{noticeId}")
    public void updateApplicationNotice(
            @PathVariable Long applicationId,
            @PathVariable Long noticeId,
            @RequestBody @Valid UpdateApplicationNoticeRequest request
    ) {

    }

    @DeleteMapping("/{noticeId}")
    public void deleteApplicationNotice(
            @PathVariable Long applicationId,
            @PathVariable Long noticeId
    ) {

    }

    @PutMapping("/{noticeId}/pinned")
    public void toggleApplicationNoticePin(
            @PathVariable Long applicationId,
            @PathVariable Long noticeId
    ) {

    }
}
