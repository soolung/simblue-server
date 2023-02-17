package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }
}
