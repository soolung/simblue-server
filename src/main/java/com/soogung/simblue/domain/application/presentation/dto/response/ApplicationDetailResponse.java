package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.type.Status;
import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
@Builder
public class ApplicationDetailResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Boolean allowsUpdatingReply;
    private Status status;
    private List<QuestionResponse> questionList;
    private List<NoticeResponse> noticeList;

    public static ApplicationDetailResponse of(Application application, List<NoticeResponse> noticeList) {
        return ApplicationDetailResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .allowsUpdatingReply(application.getAllowsUpdatingReply())
                .questionList(
                        application.getQuestionList().stream()
                                .map(QuestionResponse::of)
                                .collect(Collectors.toList()))
                .noticeList(noticeList)
                .status(application.getStatus())
                .build();
    }

    public static ApplicationDetailResponse of(Application application, List<NoticeResponse> noticeList, List<ReplyDetailResponse> replyDetailList) {
        AtomicInteger index = new AtomicInteger();

        return ApplicationDetailResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .allowsUpdatingReply(application.getAllowsUpdatingReply())
                .status(application.getStatus())
                .questionList(
                        application.getQuestionList().stream()
                                .map((q) -> QuestionResponse.of(q, replyDetailList.get(index.getAndIncrement())))
                                .collect(Collectors.toList()))
                .noticeList(noticeList)
                .build();
    }
}
