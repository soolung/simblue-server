package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_answer_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_answer_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_question_id", nullable = false)
    private ApplicationQuestion applicationQuestion;

    @Builder
    public ApplicationAnswer(String answer, ApplicationQuestion applicationQuestion) {
        this.answer = answer;
        this.applicationQuestion = applicationQuestion;
    }
}
