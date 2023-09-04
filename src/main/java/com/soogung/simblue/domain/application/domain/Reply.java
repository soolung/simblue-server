package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.type.QuestionType;
import com.soogung.simblue.domain.application.domain.type.ReplyState;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false)
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 10)
    private ReplyState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "reply_block_id")
    private ReplyBlock replyBlock;

    @Builder
    public Reply(String answer, Question question) {
        this.answer = answer;
        this.question = question;

        if (question.getType() == QuestionType.APPROVAL) {
            this.state = ReplyState.WAITING;
        }
    }

    public void putReplyBlock(ReplyBlock replyBlock) {
        this.replyBlock = replyBlock;
        replyBlock.getReplies().add(this);
    }

    public String getAnswer(UserFacade userFacade) {
        if (question.getType() == QuestionType.PEOPLE ||
                question.getType() == QuestionType.APPROVAL
        ) {
            return userFacade.getName(Long.valueOf(answer));
        }

        return answer;
    }

    public void approve() {
        this.state = ReplyState.APPROVED;
    }

    public void reject() {
        this.state = ReplyState.REJECTED;
    }
}
