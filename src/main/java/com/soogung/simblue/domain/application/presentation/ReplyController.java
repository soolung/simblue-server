package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.ReplyBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.application.service.CancelReplyService;
import com.soogung.simblue.domain.application.service.QueryReplyService;
import com.soogung.simblue.domain.application.service.ReplyApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final QueryReplyService queryReplyService;
    private final ReplyApplicationService replyApplicationService;
    private final CancelReplyService cancelReplyService;

    @GetMapping("/{reply-block-id}")
    public ApplicationDetailResponse getReply(@PathVariable("reply-block-id") Long replyBlockId) {
        return queryReplyService.execute(replyBlockId);
    }

    @PostMapping
    public void replyApplication(
            @RequestBody @Valid ReplyBlockRequest request
    ) {
        replyApplicationService.execute(request);
    }

    @DeleteMapping("/{reply-block-id}")
    public void cancelReply(@PathVariable(name = "reply-block-id") Long replyBlockId) {
        cancelReplyService.execute(replyBlockId);
    }
}
