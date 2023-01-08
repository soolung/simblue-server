package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.type.ApplicationQuestionType;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application_question_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationQuestion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_question_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String question;

    @Column(nullable = true, length = 50)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ApplicationQuestionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "applicationQuestion", cascade = CascadeType.ALL)
    private List<ApplicationAnswer> answerList = new ArrayList<>();

    @Builder
    public ApplicationQuestion(String question, String description, ApplicationQuestionType type, Application application) {
        this.question = question;
        this.description = description;
        this.type = type;
        this.application = application;
    }
}
