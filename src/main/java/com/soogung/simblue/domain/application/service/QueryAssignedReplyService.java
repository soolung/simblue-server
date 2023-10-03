package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResultResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ReplyBlockResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ReplyResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.SimpleQuestionResponse;
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
public class QueryAssignedReplyService {

    private final UserFacade userFacade;
    private final ApplicationFacade applicationFacade;
    private final OwnerRepository ownerRepository;
    private final NoticeRepository noticeRepository;
    private final QuestionRepository questionRepository;
    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationResultResponse execute(User user, Long applicationId) {
        Application application = applicationFacade.findApplicationById(applicationId);
        application.validateStatus();
        application.validatePermission(ownerRepository, user.getId());

        List<NoticeResponse> noticeList = noticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(applicationId)
                .stream().map(NoticeResponse::of)
                .collect(Collectors.toList());

        List<SimpleQuestionResponse> questionList = questionRepository.findByApplicationIdOrderById(applicationId).stream()
                .map(SimpleQuestionResponse::of)
                .collect(Collectors.toList());

        List<ReplyBlockResponse> assignedReplyList = replyBlockRepository
                .findAssignedReply(applicationId, user.getId()).stream()
                .map(this::createReplyList)
                .collect(Collectors.toList());

        return new ApplicationResultResponse(
                ApplicationResponse.of(application),
                noticeList,
                questionList,
                assignedReplyList
        );
    }

    private ReplyBlockResponse createReplyList(ReplyBlock block) {
        User user = block.getUser();

        List<ReplyResponse> replyList = new ArrayList<>();

        block.getReplies().stream()
                .collect(Collectors.groupingBy(r -> r.getQuestion().getId(), TreeMap<Long, List<Reply>>::new, Collectors.toList()))
                .forEach((k, v) -> replyList.add(getResult(k, v)));

        // TODO :: 선생님도 답변할 수 있어져서 처리 필요
        return new ReplyBlockResponse(
                user.getName(),
                user.getStudentNumber(),
                replyList
        );
    }

    private ReplyResponse getResult(Long questionId, List<Reply> request) {
        return new ReplyResponse(
                request.get(0).getId(),
                questionId,
                request.stream()
                        .map(r -> r.getAnswer(userFacade, false))
                        .collect(Collectors.joining(", ")),
                request.get(0).getState()
        );
    }
}
