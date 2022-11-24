package com.soogung.simblue.domain.emoji.presentation;

import com.soogung.simblue.domain.emoji.presentation.dto.request.CreateEmojiListRequest;
import com.soogung.simblue.domain.emoji.presentation.dto.response.EmojiListResponse;
import com.soogung.simblue.domain.emoji.service.CreateEmojiService;
import com.soogung.simblue.domain.emoji.service.QueryAllEmojiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emoji")
@RequiredArgsConstructor
public class EmojiController {

    private final QueryAllEmojiService queryAllEmojiService;
    private final CreateEmojiService createEmojiService;

    @GetMapping
    public EmojiListResponse getAllEmoji() {
        return queryAllEmojiService.execute();
    }

    @PostMapping
    public void createAllEmoji(@RequestBody CreateEmojiListRequest request) {
        createEmojiService.execute(request);
    }
}
