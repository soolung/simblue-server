package com.soogung.simblue.domain.notice.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.notice.domain.Notice;
import com.soogung.simblue.domain.user.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoticeRequest {

    @NotNull
    private Long applicationId;

    @NotNull
    @Length(min = 2, max = 50)
    private String notice;

    public Notice toEntity(Application application, Teacher teacher) {
        return Notice.builder()
                .notice(notice)
                .application(application)
                .teacher(teacher)
                .build();
    }
}
