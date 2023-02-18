package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ReplyBlock;

import java.util.List;

public interface ReplyBlockRepositoryCustom {

    List<ReplyBlock> findApplicationResult(Long applicationId);
    ReplyBlock findReplyBlockById(Long replyBlockId);
}
