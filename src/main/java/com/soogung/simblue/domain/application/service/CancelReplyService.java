package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyRepository;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelReplyService {

    private final UserFacade userFacade;
    private final ReplyBlockRepository replyBlockRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void execute(Long replyBlockId) {
        User user = userFacade.getCurrentUser();
        ReplyBlock replyBlock = replyBlockRepository.findSimpleReplyBlockById(replyBlockId);
        replyBlock.getApplication().validateStatus();
        replyBlock.getApplication().validatePeriod();
        replyBlock.validatePermission(user);

        replyRepository.deleteByReplyBlock(replyBlock);
        replyBlockRepository.deleteById(replyBlockId);
    }
}
