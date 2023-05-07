package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.application.domain.type.QuestionType;
import com.soogung.simblue.domain.application.domain.type.ReplyState;
import com.soogung.simblue.domain.application.exception.NotApprovalReplyException;
import com.soogung.simblue.domain.application.exception.ReplyAlreadyHandledException;
import com.soogung.simblue.domain.application.exception.ReplyNotFoundException;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HandleReplyService {

    private final ReplyRepository replyRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long replyId, boolean isApproved) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> ReplyNotFoundException.EXCEPTION);
        User user = userFacade.getCurrentUser();
        validate(reply, user);

        if (isApproved) {
            reply.approve();
        } else {
            reply.reject();
        }
    }

    private void validate(Reply reply, User user) {
        if (reply.getQuestion().getType() != QuestionType.APPROVAL) {
            throw NotApprovalReplyException.EXCEPTION;
        }

        if (!reply.getAnswer().equals(user.getId().toString())) {
            throw AuthorityMismatchException.EXCEPTION;
        }

        if (!(reply.getState() == ReplyState.WAITING)) {
            throw ReplyAlreadyHandledException.EXCEPTION;
        }
    }
}
