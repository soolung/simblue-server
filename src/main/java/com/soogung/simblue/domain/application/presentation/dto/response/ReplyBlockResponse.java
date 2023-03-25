package com.soogung.simblue.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReplyBlockResponse {
    private String name;
    private String studentNumber;
    private List<ReplyResponse> replyList;
}
