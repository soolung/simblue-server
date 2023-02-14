package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.Reply;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequest {

    @NotNull
    private Long id;

    private List<String> replyList;

    public Reply toEntity(Question question, ReplyBlock block, String a) {
        return Reply.builder()
                .answer(a)
                .question(question)
                .replyBlock(block)
                .build();
    }
}
