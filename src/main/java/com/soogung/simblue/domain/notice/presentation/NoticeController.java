package com.soogung.simblue.domain.notice.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.UpdateApplicationNoticeRequest;
import com.soogung.simblue.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.soogung.simblue.domain.notice.service.CreateNoticeService;
import com.soogung.simblue.domain.notice.service.DeleteNoticeService;
import com.soogung.simblue.domain.notice.service.ToggleNoticePinService;
import com.soogung.simblue.domain.notice.service.UpdateNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final CreateNoticeService createNoticeService;
    private final UpdateNoticeService updateNoticeService;
    private final DeleteNoticeService deleteNoticeService;
    private final ToggleNoticePinService toggleNoticePinService;

    @PostMapping
    public void createApplicationNotice(@RequestBody @Valid CreateNoticeRequest request) {
        createNoticeService.execute(request);
    }

    @PutMapping("/{id}")
    public void updateApplicationNotice(
            @PathVariable Long id,
            @RequestBody @Valid UpdateApplicationNoticeRequest request
    ) {
        updateNoticeService.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteApplicationNotice(@PathVariable Long id) {
        deleteNoticeService.execute(id);
    }

    @PutMapping("/{id}/pinned")
    public void toggleApplicationNoticePin(@PathVariable Long id) {
        toggleNoticePinService.execute(id);
    }
}
