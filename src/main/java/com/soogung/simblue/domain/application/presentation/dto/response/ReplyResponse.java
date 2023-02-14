package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Reply;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyResponse {

    private Long questionId;
    private String answer;

    public static ReplyResponse of(Reply request) {
        return ReplyResponse.builder()
                .questionId(request.getQuestion().getId())
                .answer(request.getAnswer())
                .build();
    }
}
