package com.soogung.simblue.domain.group.presentation;

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
public class GroupController {

    private final CreateGroupService createGroupService;

    private final QueryGroupService queryGroupService;

    @PostMapping
    public void createGroup(@RequestBody @Valid GroupRequest request) {
        createGroupService.execute(request);
    }

    @GetMapping
    public GroupListResponse getGroupList(){
        return queryGroupService.execute();
    }

}
