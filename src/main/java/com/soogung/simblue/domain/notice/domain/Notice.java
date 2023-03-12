package com.soogung.simblue.domain.notice.domain;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
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
    public Notice(String notice, Application application, Teacher teacher) {
        this.notice = notice;
        this.isPinned = false;
        this.application = application;
        this.teacher = teacher;
    }

    public void updateNotice(String notice) {
        this.notice = notice;
    }

    public void toggleIsPinned() {
        this.isPinned = !isPinned;
    }
}
