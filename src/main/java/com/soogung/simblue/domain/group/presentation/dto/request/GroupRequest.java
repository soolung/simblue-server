package com.soogung.simblue.domain.group.presentation.dto.request;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.type.GroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {

    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    @NotNull
    private GroupType type;

    public Group toEntity() {
        return Group.builder()
                .name(name)
                .type(type)
                .build();
    }

}
