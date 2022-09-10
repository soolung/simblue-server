package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_request_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_request_id")
    private Long id;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_question_id")
    private ApplicationQuestion applicationQuestion;

    @Builder
    public ApplicationRequest(String answer, Student student, Application application, ApplicationQuestion applicationQuestion) {
        this.answer = answer;
        this.student = student;
        this.application = application;
        this.applicationQuestion = applicationQuestion;
    }
}
