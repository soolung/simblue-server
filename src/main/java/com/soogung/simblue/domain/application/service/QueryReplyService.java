package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.facade.ReplyBlockFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ReplyListResponse;
import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryReplyService {

    private final UserFacade userFacade;
    private final ReplyBlockFacade replyBlockFacade;
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public ApplicationDetailResponse execute(Long id) {
        User student = userFacade.getCurrentUser();
        ReplyBlock replyBlock = replyBlockFacade.getReplyBlock(id);
        Application application = replyBlock.getApplication();
        application.validateStatus();
        replyBlock.validatePermission(student);

        List<ReplyListResponse> replyList = createReplyDetailList(replyBlock);

        List<NoticeResponse> noticeList = noticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(id)
                .stream().map(NoticeResponse::of).collect(Collectors.toList());

        return ApplicationDetailResponse.of(
                application,
                noticeList,
                replyList
        );
    }

    private List<ReplyListResponse> createReplyDetailList(ReplyBlock block) {
        List<ReplyListResponse> replyList = new ArrayList<>();

        block.getReplies().stream()
                .collect(Collectors.groupingBy(r -> r.getQuestion().getId(), TreeMap<Long, List<Reply>>::new, Collectors.toList()))
                .forEach((k, v) -> replyList.add(getResult(k, v)));

        return replyList;
    }

    private ReplyListResponse getResult(Long questionId, List<Reply> request) {
        return new ReplyListResponse(
                questionId,
                request.stream()
                        .map(r -> r.getAnswer(userFacade))
                        .collect(Collectors.toList())
        );
    }
}
