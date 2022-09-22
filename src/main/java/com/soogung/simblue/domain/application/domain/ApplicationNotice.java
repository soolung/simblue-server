package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_notice_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationNotice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicaiton_notice_tbl")
    private Long id;

    @Column(nullable = false, length = 50)
    private String notice;

    @Column(nullable = false)
    private Boolean isPinned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Builder
    public ApplicationNotice(String notice, Application application, Teacher teacher) {
        this.notice = notice;
        this.isPinned = false;
        this.application = application;
        this.teacher = teacher;
    }

    public void updateNotice(String notice) {
        this.notice = notice;
    }
}
