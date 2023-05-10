package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.ReplyBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.application.service.CancelReplyService;
import com.soogung.simblue.domain.application.service.HandleReplyService;
import com.soogung.simblue.domain.application.service.QueryReplyService;
import com.soogung.simblue.domain.application.service.ReplyApplicationService;
import com.soogung.simblue.domain.application.service.UpdateReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final QueryReplyService queryReplyService;
    private final ReplyApplicationService replyApplicationService;
    private final UpdateReplyService updateReplyService;
    private final HandleReplyService handleReplyService;
    private final CancelReplyService cancelReplyService;

    @GetMapping("/{reply-block-id}")
    public ApplicationDetailResponse getReply(@PathVariable("reply-block-id") Long replyBlockId) {
        return queryReplyService.execute(replyBlockId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void replyApplication(
            @RequestBody @Valid ReplyBlockRequest request
    ) {
        replyApplicationService.execute(request);
    }

    @PutMapping("/{reply-block-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReply(
            @PathVariable(name = "reply-block-id") Long replyBlockId,
            @RequestBody @Valid ReplyBlockRequest request
    ) {
        updateReplyService.execute(replyBlockId, request);
    }

    @PutMapping("/{reply-id}/handle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleReply(
            @PathVariable(name = "reply-id") Long replyId,
            @RequestParam boolean approve
    ) {
        handleReplyService.execute(replyId, approve);
    }

    @DeleteMapping("/{reply-block-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReply(@PathVariable(name = "reply-block-id") Long replyBlockId) {
        cancelReplyService.execute(replyBlockId);
    }
}
