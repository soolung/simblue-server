package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.Teacher;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerResponse {

    private Long teacherId;
    private String name;

    public static OwnerResponse of(Teacher teacher) {
        return OwnerResponse.builder()
                .teacherId(teacher.getId())
                .name(teacher.getUser().getName())
                .build();
    }
}
