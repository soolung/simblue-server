package com.soogung.simblue.domain.group.presentation.dto.response;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.type.GroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GroupListResponse {

    private List<GroupResponse> groupList;

}
