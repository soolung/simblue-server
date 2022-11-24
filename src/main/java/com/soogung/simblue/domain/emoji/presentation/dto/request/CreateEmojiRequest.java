package com.soogung.simblue.domain.emoji.presentation.dto.request;

import com.soogung.simblue.domain.emoji.domain.Emoji;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmojiRequest {

    String emoji;

    public Emoji toEntity() {
        return Emoji.builder()
                .emoji(emoji)
                .build();
    }
}
