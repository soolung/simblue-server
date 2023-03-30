package com.soogung.simblue.domain.group.presentation.dto.response;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.type.GroupType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupResponse {

    private Long id;
    private String name;
    private GroupType type;

    public static GroupResponse of(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .type(group.getType())
                .build();
    }

}
