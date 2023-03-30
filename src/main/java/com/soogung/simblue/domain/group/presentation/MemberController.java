package com.soogung.simblue.domain.group.presentation;

import com.soogung.simblue.domain.group.presentation.dto.request.JoinGroupRequest;
import com.soogung.simblue.domain.group.service.JoinGroupService;
import com.soogung.simblue.domain.group.presentation.dto.request.GroupRequest;
import com.soogung.simblue.domain.group.presentation.dto.response.GroupListResponse;
import com.soogung.simblue.domain.group.service.CreateGroupService;
import com.soogung.simblue.domain.group.service.QueryGroupService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class MemberController {

    private final CreateGroupService createGroupService;
    private final QueryGroupService queryGroupService;
    private final JoinGroupService joinGroupService;

<<<<<<< HEAD:src/main/java/com/soogung/simblue/domain/group/presentation/GroupController.java
    @PostMapping
    public void createGroup(@RequestBody @Valid GroupRequest request) {
        createGroupService.execute(request);
    }

    @GetMapping
    public GroupListResponse getGroupList(){
        return queryGroupService.execute();
    }
    
    @PutMapping("/{id}/add")
=======
    @PostMapping("/{id}/join")
>>>>>>> 4e6690e (ADD :: 리뷰 반영 (#63)):src/main/java/com/soogung/simblue/domain/group/presentation/MemberController.java
    public void addMember(@PathVariable Long id,@RequestBody @Valid JoinGroupRequest request) {
        joinGroupService.execute(id, request);
    }
}
