package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.type.ReplyState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyResponse {

    private Long replyId;
    private Long questionId;
    private String reply;
    private ReplyState state;
}
