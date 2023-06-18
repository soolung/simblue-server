package com.soogung.simblue.domain.application.domain.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.type.QuestionType;
import com.soogung.simblue.domain.application.domain.type.ReplyState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.soogung.simblue.domain.application.domain.QApplication.application;
import static com.soogung.simblue.domain.application.domain.QReply.reply;
import static com.soogung.simblue.domain.application.domain.QReplyBlock.replyBlock;
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
                .join(replyBlock.user, user).fetchJoin()
                .where(replyBlock.application.id.eq(applicationId))
                .orderBy(
                        user.studentNumber.asc(),
                        reply.id.asc()
                )
                .distinct()
                .fetch();
    }

    @Override
    public List<ReplyBlock> findAssignedReply(Long applicationId, Long userId) {
        return queryFactory
                .selectFrom(replyBlock)
                .join(replyBlock.replies, reply).fetchJoin()
                .where(
                        replyBlock.application.id.eq(applicationId).and(
                                replyBlock.id.in(
                                        JPAExpressions
                                                .select(reply.replyBlock.id)
                                                .from(reply)
                                                .where(
                                                        reply.answer.eq(String.valueOf(userId))
                                                                .and(reply.question.type.eq(QuestionType.APPROVAL))
                                                                .and(reply.state.eq(ReplyState.WAITING))
                                                )

                                )
                        )
                )
                .distinct()
                .fetch();
    }

    @Override
    public ReplyBlock findSimpleReplyBlockById(Long replyBlockId) {
        return queryFactory
                .selectFrom(replyBlock)
                .join(replyBlock.user, user).fetchJoin()
                .join(replyBlock.application, application).fetchJoin()
                .where(replyBlock.id.eq(replyBlockId))
                .fetchOne();
    }

    @Override
    public Optional<ReplyBlock> findReplyBlockById(Long replyBlockId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(replyBlock)
                        .join(replyBlock.replies, reply).fetchJoin()
                        .join(replyBlock.user, user).fetchJoin()
                        .join(replyBlock.application, application).fetchJoin()
                        .where(replyBlock.id.eq(replyBlockId))
                        .fetchOne()
        );
    }
}
