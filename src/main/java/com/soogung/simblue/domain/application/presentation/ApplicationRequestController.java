package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequestBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResultResponse;
import com.soogung.simblue.domain.application.service.CancelApplicationRequestService;
import com.soogung.simblue.domain.application.service.QueryApplicationResultService;
import com.soogung.simblue.domain.application.service.RespondApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application/{id}/request")
@RequiredArgsConstructor
public class ApplicationRequestController {

    private final RespondApplicationService respondApplicationService;
    private final CancelApplicationRequestService cancelApplicationRequestService;
    private final QueryApplicationResultService queryApplicationResultService;

    @GetMapping
    public ApplicationResultResponse getApplicationResult(@PathVariable Long id) {
        return queryApplicationResultService.execute(id);
    }

    @PostMapping
    public void respondApplication(
            @PathVariable Long id,
            @RequestBody @Valid ApplicationRequestBlockRequest request
    ) {
        respondApplicationService.execute(id, request);
    }

    @DeleteMapping
    public void cancelApplicationRequest(@PathVariable Long id) {
        cancelApplicationRequestService.execute(id);
    }
}
