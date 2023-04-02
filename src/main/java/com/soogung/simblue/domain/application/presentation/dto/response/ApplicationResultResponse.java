package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationResultResponse {
    private ApplicationResponse application;
    private List<NoticeResponse> noticeList;
    private List<SimpleQuestionResponse> questionList;
    private List<ReplyBlockResponse> resultList;
}
