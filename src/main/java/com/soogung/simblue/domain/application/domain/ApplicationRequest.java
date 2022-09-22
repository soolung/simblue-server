package com.soogung.simblue.domain.application.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_request_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_request_id")
    private Long id;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_question_id")
    private ApplicationQuestion applicationQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_request_block_id")
    private ApplicationRequestBlock applicationRequestBlock;

    @Builder
    public ApplicationRequest(String answer, ApplicationQuestion applicationQuestion, ApplicationRequestBlock applicationRequestBlock) {
        this.answer = answer;
        this.applicationQuestion = applicationQuestion;
        this.applicationRequestBlock = applicationRequestBlock;
    }
}
