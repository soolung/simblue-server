package com.soogung.simblue.domain.group.presentation;

import com.soogung.simblue.domain.group.presentation.dto.request.AddGroupRequest;
import com.soogung.simblue.domain.group.service.AddGroupService;
import com.soogung.simblue.domain.group.presentation.dto.request.GroupRequest;
import com.soogung.simblue.domain.group.presentation.dto.response.GroupListResponse;
import com.soogung.simblue.domain.group.service.CreateGroupService;
import com.soogung.simblue.domain.group.service.QueryGroupService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class MemberController {

    private final CreateGroupService createGroupService;
    private final QueryGroupService queryGroupService;
    private final AddGroupService addGroupService;

    @PostMapping
    public void createGroup(@RequestBody @Valid GroupRequest request) {
        createGroupService.execute(request);
    }

    @GetMapping
    public GroupListResponse getGroupList(){
        return queryGroupService.execute();
    }
    //ㅁㄴㅇㄴ
    @PostMapping("/{id}/add")
    public void addMember(@PathVariable Long id,@RequestBody @Valid AddGroupRequest request) {
        addGroupService.execute(id, request);
    }
}
