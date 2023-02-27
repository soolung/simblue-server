package com.soogung.simblue.domain.application.facade;

import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.exception.ReplyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReplyBlockFacade {

    private final ReplyBlockRepository replyBlockRepository;

    @Transactional(readOnly = true)
    public ReplyBlock getReplyBlock(Long replyBlockId) {
        return replyBlockRepository.findReplyBlockById(replyBlockId)
                .orElseThrow(() -> ReplyNotFoundException.EXCEPTION);
    }
}
