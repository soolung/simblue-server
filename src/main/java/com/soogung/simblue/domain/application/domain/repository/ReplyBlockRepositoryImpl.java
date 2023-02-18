package com.soogung.simblue.domain.application.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soogung.simblue.domain.application.domain.QReply.reply;
import static com.soogung.simblue.domain.application.domain.QReplyBlock.replyBlock;
import static com.soogung.simblue.domain.user.domain.QStudent.student;
import static com.soogung.simblue.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ReplyBlockRepositoryImpl implements ReplyBlockRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReplyBlock> findApplicationResult(Long applicationId) {
        return queryFactory
                .selectFrom(replyBlock)
                .join(replyBlock.replies, reply).fetchJoin()
                .join(replyBlock.student, student).fetchJoin()
                .join(student.user, user).fetchJoin()
                .where(replyBlock.application.id.eq(applicationId))
                .orderBy(
                        student.studentNumber.asc(),
                        reply.id.asc()
                )
                .distinct()
                .fetch();
    }

    @Override
    public ReplyBlock findReplyBlockById(Long replyBlockId) {
        return queryFactory
                .selectFrom(replyBlock)
                .join(replyBlock.replies, reply).fetchJoin()
                .join(replyBlock.student, student).fetchJoin()
                .where(replyBlock.id.eq(replyBlockId))
                .fetchOne();
    }
}
