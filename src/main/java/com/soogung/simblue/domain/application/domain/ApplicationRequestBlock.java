package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_request_blcok_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationRequestBlock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_request_block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Builder
    public ApplicationRequestBlock(Student student, Application application) {
        this.student = student;
        this.application = application;
    }
}
