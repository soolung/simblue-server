package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.FilterListRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.FilterRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResultResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ReplyBlockResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ReplyResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.SimpleQuestionResponse;
import com.soogung.simblue.domain.notice.domain.repository.NoticeRepository;
import com.soogung.simblue.domain.notice.presentation.dto.response.NoticeResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.global.util.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryApplicationResultService {

    private final UserFacade userFacade;
    private final ApplicationFacade applicationFacade;
    private final NoticeRepository noticeRepository;
    private final OwnerRepository ownerRepository;
    private final QuestionRepository questionRepository;
    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationResultResponse execute(User user, Long id, FilterListRequest filterList) {
        Application application = applicationFacade.findApplicationById(id);
        application.validateStatus();
        application.validatePermission(ownerRepository, user.getId());

        List<NoticeResponse> noticeList = noticeRepository.findAllByApplicationIdOrderByIsPinnedDesc(id)
                .stream().map(NoticeResponse::of)
                .collect(Collectors.toList());

        List<SimpleQuestionResponse> questionList = questionRepository.findByApplicationIdOrderById(id).stream()
                .map(SimpleQuestionResponse::of)
                .collect(Collectors.toList());

        List<ReplyBlockResponse> resultList = replyBlockRepository
                .findApplicationResult(id).stream()
                .filter(r -> filtering(r, filterList))
                .map(this::createReplyList)
                .filter(r -> filteringContains(r, filterList))
                .collect(Collectors.toList());

        return new ApplicationResultResponse(
                ApplicationResponse.of(application),
                noticeList,
                questionList,
                resultList
        );
    }

    private boolean filtering(ReplyBlock block, FilterListRequest request) {
        if (validate(request)) return true;

        for (FilterRequest filter : request.getFilterList()) {
            if (filter.getOperator() == Operator.CONTAINS) {
                continue;
            }

            Reply reply = getReplyByQuestionId(block.getReplies(), filter.getQuestionId());
            if (!filter.getOperator().getComparator()
                    .execute(filter.getTarget(), reply.getAnswer())) {
                return false;
            }
        }

        return true;
    }

    private boolean filteringContains(ReplyBlockResponse block, FilterListRequest request) {
        if (validate(request)) return true;

        for (FilterRequest filter : request.getFilterList()) {
            if (filter.getOperator() != Operator.CONTAINS) {
                continue;
            }

            ReplyResponse reply = getReplyResponseByQuestionId(block.getReplyList(), filter.getQuestionId());
            if (!filter.getOperator().getComparator()
                    .execute(filter.getTarget(), reply.getReply())) {
                return false;
            }
        }

        return true;
    }

    private boolean validate(FilterListRequest request) {
        return request == null ||
                request.getFilterList() == null ||
                request.getFilterList().isEmpty();
    }

    private Reply getReplyByQuestionId(List<Reply> replyList, Long questionId) {
        return replyList.stream()
                .filter(r -> r.getQuestion().getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new SimblueException(ErrorCode.BAD_REQUEST));
    }

    private ReplyResponse getReplyResponseByQuestionId(List<ReplyResponse> replyList, Long questionId) {
        return replyList.stream()
                .filter(r -> r.getQuestionId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new SimblueException(ErrorCode.BAD_REQUEST));
    }

    private ReplyBlockResponse createReplyList(ReplyBlock block) {
        User student = block.getUser();

        List<ReplyResponse> replyList = new ArrayList<>();

        block.getReplies().stream()
                .collect(Collectors.groupingBy(r -> r.getQuestion().getId(), TreeMap<Long, List<Reply>>::new, Collectors.toList()))
                .forEach((k, v) -> replyList.add(getResult(k, v)));

        // TODO :: 선생님도 답변할 수 있어져서 처리 필요
        return new ReplyBlockResponse(
                student.getName(),
                student.getStudentNumber(),
                replyList
        );
    }

    private ReplyResponse getResult(Long questionId, List<Reply> request) {
        return new ReplyResponse(
                request.get(0).getId(),
                questionId,
                request.stream()
                        .map(r -> r.getAnswer(userFacade))
                        .collect(Collectors.joining(", ")),
                request.get(0).getState()
        );
    }
}
