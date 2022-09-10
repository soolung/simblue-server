package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.type.ApplicationQuestionType;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table("application_question_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationQuestion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_question_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ApplicationQuestionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Builder
    public ApplicationQuestion(String question, ApplicationQuestionType type) {
        this.question = question;
        this.type = type;
    }
}
