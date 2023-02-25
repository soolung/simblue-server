package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationRequest;
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
    private final QueryApplicationCreationFormService queryApplicationCreationFormService;
    private final QueryMyApplicationService queryMyApplicationService;
    private final QueryApplicationResultService queryApplicationResultService;

    @PostMapping
    public void createApplication(@RequestBody @Valid CreateApplicationRequest request) {
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
    public ApplicationFormResponse getApplicationCreationForm(@PathVariable Long id) {
        return queryApplicationCreationFormService.execute(id);
    }

    @GetMapping("/my")
    public ApplicationListResponse getMyApplication() {
        return queryMyApplicationService.execute();
    }

    @GetMapping("/{id}/result")
    public ResultBlockResponse getApplicationResult(@PathVariable Long id) {
        return queryApplicationResultService.execute(id);
    }
}
