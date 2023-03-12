package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Owner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Builder
    public Owner(Teacher teacher, Application application) {
        this.teacher = teacher;
        this.application = application;
    }
}
