package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.type.QuestionType;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_block_id")
    private ReplyBlock replyBlock;

    @Builder
    public Reply(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    public void putReplyBlock(ReplyBlock replyBlock) {
        this.replyBlock = replyBlock;
        replyBlock.getReplies().add(this);
    }

    public String getAnswer(UserFacade userFacade) {
        if (question.getType() == QuestionType.PEOPLE) {
            return userFacade.getName(Long.valueOf(answer));
        }

        return answer;
    }
}
