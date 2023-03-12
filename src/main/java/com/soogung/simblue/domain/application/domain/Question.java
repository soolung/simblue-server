package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.type.QuestionType;
import com.soogung.simblue.domain.application.exception.QuestionIsRequiredException;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyRequest;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String question;

    @Column(nullable = true, length = 50)
    private String description;

    @Column(nullable = false)
    private Boolean isRequired;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuestionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    @Builder
    public Question(String question, String description, Boolean isRequired, QuestionType type, Application application) {
        this.question = question;
        this.description = description;
        this.isRequired = isRequired;
        this.type = type;
        this.application = application;
    }

    public void validateAnswer(ReplyRequest request) {
        if (isRequired && request.getReplyDetailList().isEmpty()) {
            throw QuestionIsRequiredException.EXCEPTION;
        }
    }

    public void validateAnswer(String answer) {
        if (isRequired && (answer == null || answer.equals(""))) {
            throw QuestionIsRequiredException.EXCEPTION;
        }
    }
}
