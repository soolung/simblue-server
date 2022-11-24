package com.soogung.simblue.domain.emoji.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmojiListRequest {

    private List<CreateEmojiRequest> emojiList;
}
