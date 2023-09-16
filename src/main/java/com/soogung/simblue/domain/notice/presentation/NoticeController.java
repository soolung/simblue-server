package com.soogung.simblue.domain.notice.presentation;

import com.soogung.simblue.domain.notice.presentation.dto.request.UpdateNoticeRequest;
import com.soogung.simblue.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.soogung.simblue.domain.notice.service.CreateNoticeService;
import com.soogung.simblue.domain.notice.service.DeleteNoticeService;
import com.soogung.simblue.domain.notice.service.ToggleNoticePinService;
import com.soogung.simblue.domain.notice.service.UpdateNoticeService;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import com.soogung.simblue.global.auth.annotation.Authority;
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
    public void createApplicationNotice(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestBody @Valid CreateNoticeRequest request
    ) {
        createNoticeService.execute(user, request);
    }

    @PutMapping("/{id}")
    public void updateApplicationNotice(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @PathVariable Long id,
            @RequestBody @Valid UpdateNoticeRequest request
    ) {
        updateNoticeService.execute(user, id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteApplicationNotice(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @PathVariable Long id
    ) {
        deleteNoticeService.execute(user, id);
    }

    @PutMapping("/{id}/pinned")
    public void toggleApplicationNoticePin(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @PathVariable Long id
    ) {
        toggleNoticePinService.execute(user, id);
    }
}
