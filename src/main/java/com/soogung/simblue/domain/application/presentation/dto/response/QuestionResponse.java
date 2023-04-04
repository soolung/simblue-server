package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.type.QuestionType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
public class QuestionResponse {

    private Long id;
    private String question;
    private String description;
    private Boolean isRequired;
    private QuestionType type;
    private List<AnswerResponse> answerList;
    private List<String> replyList;

    public static QuestionResponse of(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .description(question.getDescription())
                .isRequired(question.getIsRequired())
                .type(question.getType())
                .answerList(
                        question.getType().isHasAnswer() ?
                                question.getAnswerList().stream()
                                        .map(AnswerResponse::of)
                                        .collect(Collectors.toList()) : null)
                .build();
    }

    public static QuestionResponse of(Question question, ReplyListResponse replyList) {
        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .description(question.getDescription())
                .isRequired(question.getIsRequired())
                .type(question.getType())
                .answerList(
                        question.getType().isHasAnswer() ?
                                question.getAnswerList().stream()
                                        .map(AnswerResponse::of)
                                        .collect(Collectors.toList()) : null)
                .replyList(Objects.nonNull(replyList) ? replyList.getReplyList() : null)
                .build();
    }
}
