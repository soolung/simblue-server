package com.soogung.simblue.domain.group.presentation;

import com.soogung.simblue.domain.group.presentation.dto.request.JoinGroupRequest;
import com.soogung.simblue.domain.group.service.JoinGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class MemberController {

    private final JoinGroupService joinGroupService;

    @PostMapping("/{id}/join")
    public void addMember(@PathVariable Long id,@RequestBody @Valid JoinGroupRequest request) {
        joinGroupService.execute(id, request);
    }
}
