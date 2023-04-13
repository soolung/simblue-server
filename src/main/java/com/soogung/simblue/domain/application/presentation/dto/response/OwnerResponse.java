package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerResponse {

    private Long teacherId;
    private String name;

    public static OwnerResponse of(User teacher) {
        return OwnerResponse.builder()
                .teacherId(teacher.getId())
                .name(teacher.getName())
                .build();
    }
}
