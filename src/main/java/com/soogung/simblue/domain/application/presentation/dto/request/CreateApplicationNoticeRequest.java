package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationNoticeRequest {

    @NotNull
    private Long applicationId;

    @NotNull
    @Length(min = 2, max = 50)
    private String notice;

    public ApplicationNotice toEntity(Application application, Teacher teacher) {
        return ApplicationNotice.builder()
                .notice(notice)
                .application(application)
                .teacher(teacher)
                .build();
    }
}
