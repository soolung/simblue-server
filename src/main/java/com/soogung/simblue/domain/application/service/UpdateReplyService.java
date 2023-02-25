package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.ReplyRequest;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateReplyService {

    private final UserFacade userFacade;
    private final ReplyBlockRepository replyBlockRepository;
    private final ReplyRepository replyRepository;
    private final ApplicationFacade applicationFacade;

    @Transactional
    public void execute(Long replyBlockId, ReplyBlockRequest request) {
        ReplyBlock block = replyBlockRepository.findReplyBlockById(replyBlockId);
        Application application = block.getApplication();
        Student student = userFacade.findStudentByUser(userFacade.getCurrentUser());
        block.validatePermission(student);
        application.validatePeriod();
        application.validateReplyUpdatable();

        replyRepository.deleteByReplyBlock(block);
        replyRepository.flush();
        replyRepository.saveAll(
                toReplyFrom(request.getReplyList(), block));
    }

    private List<Reply> toReplyFrom(
            List<ReplyRequest> replyRequestList,
            ReplyBlock block
    ) {
        return replyRequestList.stream()
                .map(r -> {
                    Question q = applicationFacade.findQuestionById(r.getId());
                    q.validateAnswer(r);
                    return r.getReplyDetailList().stream()
                            .map(a -> createReplyFrom(r, block, q, a))
                            .collect(Collectors.toList());
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Reply createReplyFrom(
            ReplyRequest request,
            ReplyBlock block,
            Question question,
            String answer
    ) {
        question.validateAnswer(answer);
        Reply reply = request.toEntity(question, block, answer);
        reply.putReplyBlock(block);
        return reply;
    }
}
