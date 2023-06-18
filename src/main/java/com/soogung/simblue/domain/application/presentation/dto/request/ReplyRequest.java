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

    private List<String> replyDetailList;

    public Reply toEntity(Question question, String a) {
        return Reply.builder()
                .answer(a)
                .question(question)
                .build();
    }
}
