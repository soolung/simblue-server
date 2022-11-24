package com.soogung.simblue.domain.emoji.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmojiListResponse {
    private List<EmojiResponse> emojiList;
}
