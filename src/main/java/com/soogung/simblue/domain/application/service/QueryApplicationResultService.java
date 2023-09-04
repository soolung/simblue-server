package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.QuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.*;
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
public class QueryApplicationResultService {

    private final UserFacade userFacade;
    private final ApplicationFacade applicationFacade;
    private final NoticeRepository noticeRepository;
    private final OwnerRepository ownerRepository;
    private final QuestionRepository questionRepository;
    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationResultResponse execute(Long id) {
        User user = userFacade.getCurrentUser();
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
                .map(this::createReplyList)
                .collect(Collectors.toList());

        return new ApplicationResultResponse(
                ApplicationResponse.of(application),
                noticeList,
                questionList,
                resultList
        );
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
