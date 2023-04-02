package com.soogung.simblue.domain.group.presentation;

import com.soogung.simblue.domain.group.presentation.dto.request.AddGroupRequest;
import com.soogung.simblue.domain.group.service.AddGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class MemberController {

    private final AddGroupService addGroupService;

    @PostMapping("/{id}/add")
    public void addMember(@PathVariable Long id,@RequestBody @Valid AddGroupRequest request) {
        addGroupService.execute(id, request);
    }
}
