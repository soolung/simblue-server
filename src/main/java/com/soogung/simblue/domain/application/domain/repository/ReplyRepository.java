package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    void deleteByReplyBlock(ReplyBlock replyBlock);
}
