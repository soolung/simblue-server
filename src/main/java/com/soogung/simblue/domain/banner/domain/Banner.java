package com.soogung.simblue.domain.banner.domain;

import com.soogung.simblue.domain.banner.domain.type.Status;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_banner")
@Entity
public class Banner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(nullable = false)
    private String imageUri;

    @Column(nullable = true)
    private String linkTo;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Builder
    public Banner(String imageUri, String linkTo, Teacher teacher) {
        this.imageUri = imageUri;
        this.linkTo = linkTo;
        this.status = Status.ACTIVE;
        this.teacher = teacher;
    }
}