package com.soogung.simblue.domain.emoji.service;

import com.soogung.simblue.domain.emoji.domain.repository.EmojiRepository;
import com.soogung.simblue.domain.emoji.presentation.dto.request.CreateEmojiListRequest;
import com.soogung.simblue.domain.emoji.presentation.dto.request.CreateEmojiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateEmojiService {

    private final EmojiRepository emojiRepository;

    public void execute(CreateEmojiListRequest request) {
        emojiRepository.saveAll(
                request.getEmojiList().stream()
                        .map(CreateEmojiRequest::toEntity)
                        .collect(Collectors.toList())
        );
    }
}
