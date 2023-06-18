package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ReplyBlock;

import java.util.List;
import java.util.Optional;

public interface ReplyBlockRepositoryCustom {

    List<ReplyBlock> findApplicationResult(Long applicationId);
    List<ReplyBlock> findAssignedReply(Long applicationId, Long userId);
    ReplyBlock findSimpleReplyBlockById(Long replyBlockId);
    Optional<ReplyBlock> findReplyBlockById(Long replyBlockId);
}
