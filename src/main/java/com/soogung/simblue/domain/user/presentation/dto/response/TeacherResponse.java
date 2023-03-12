package com.soogung.simblue.domain.user.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.Teacher;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherResponse {

    private Long teacherId;
    private String name;

    public static TeacherResponse of(Teacher teacher) {
        return TeacherResponse.builder()
                .teacherId(teacher.getId())
                .name(teacher.getUser().getName())
                .build();
    }
}
