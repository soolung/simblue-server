package com.soogung.simblue.domain.emoji.presentation.dto.response;

import com.soogung.simblue.domain.emoji.domain.Emoji;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmojiResponse {

    private String emoji;

    public EmojiResponse(Emoji emoji) {
        this.emoji = emoji.getEmoji();
    }
}
