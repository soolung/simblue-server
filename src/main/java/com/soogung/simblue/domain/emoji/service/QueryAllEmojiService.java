package com.soogung.simblue.domain.emoji.service;

import com.soogung.simblue.domain.emoji.domain.repository.EmojiRepository;
import com.soogung.simblue.domain.emoji.presentation.dto.response.EmojiListResponse;
import com.soogung.simblue.domain.emoji.presentation.dto.response.EmojiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllEmojiService {

    private final EmojiRepository emojiRepository;

    public EmojiListResponse execute() {
        return new EmojiListResponse(
                emojiRepository.findAll().stream().map(EmojiResponse::new).collect(Collectors.toList())
        );
    }
}
