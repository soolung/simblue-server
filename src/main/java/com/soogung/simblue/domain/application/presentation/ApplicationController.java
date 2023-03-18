package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.*;
import com.soogung.simblue.domain.application.service.*;
import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final CreateApplicationService applicationService;
    private final QueryDeadlineApplicationService queryDeadlineApplicationService;
    private final QueryLatestApplicationService queryLatestApplicationService;
    private final QueryAlwaysApplicationService queryAlwaysApplicationService;
    private final QueryPagingApplication queryPagingApplication;
    private final QueryApplicationDetailService queryApplicationDetailService;
    private final QueryApplicationFormService queryApplicationFormService;
    private final QueryMyApplicationService queryMyApplicationService;
    private final QueryApplicationResultService queryApplicationResultService;
    private final UpdateApplicationService updateApplicationService;
    private final DeleteApplicationService deleteApplicationService;
    private final CloseApplicationService closeApplicationService;

    @PostMapping
    public void createApplication(@RequestBody @Valid ApplicationRequest request) {
        applicationService.execute(request);
    }

    @GetMapping
    public List<ApplicationResponse> getApplications(@RequestParam(name = "type", required = true) String applicationType) {
        if (applicationType.equals("deadline")) {
            return queryDeadlineApplicationService.execute();
        } else if (applicationType.equals("latest")) {
            return queryLatestApplicationService.execute();
        } else if (applicationType.equals("always")) {
            return queryAlwaysApplicationService.execute();
        } else {
            throw new SimblueException(ErrorCode.BAD_REQUEST);
        }
    }

    @GetMapping("/paging")
    public ApplicationListResponse getPagingApplication(@PageableDefault(size = 4) Pageable pageable) {
        return queryPagingApplication.execute(pageable);
    }

    @GetMapping("/{id}")
    public ApplicationDetailResponse getApplicationDetail(@PathVariable Long id) {
        return queryApplicationDetailService.execute(id);
    }

    @GetMapping("/{id}/form")
    public ApplicationFormResponse getApplicationForm(@PathVariable Long id) {
        return queryApplicationFormService.execute(id);
    }

    @GetMapping("/my")
    public ApplicationStatusResponse getMyApplication() {
        return queryMyApplicationService.execute();
    }

    @GetMapping("/{id}/result")
    public ResultBlockResponse getApplicationResult(@PathVariable Long id) {
        return queryApplicationResultService.execute(id);
    }

    @PutMapping("/{id}")
    public void updateApplication(
            @PathVariable Long id,
            @RequestBody @Valid ApplicationRequest request
    ) {
        updateApplicationService.execute(id, request);
    }

    @PutMapping("/{id}/close")
    public void closeApplication(@PathVariable Long id) {
        closeApplicationService.execute(id);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        deleteApplicationService.execute(id);
    }
}
