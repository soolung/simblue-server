package com.soogung.simblue.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReplyListResponse {

    private Long questionId;
    private List<String> replyList;
}
