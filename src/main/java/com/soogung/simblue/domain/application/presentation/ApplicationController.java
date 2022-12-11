package com.soogung.simblue.domain.application.presentation;

import com.soogung.simblue.domain.application.presentation.dto.request.CreateApplicationRequest;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationDetailResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationListResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
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
    private final QueryFourLatestApplication queryFourLatestApplication;
    private final QueryApplicationDetailService queryApplicationDetailService;
    private final QueryMyApplicationService queryMyApplicationService;

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

    @GetMapping("/four")
    public ApplicationListResponse getFourLatestApplication(@PageableDefault(size = 4) Pageable pageable) {
        return queryFourLatestApplication.execute(pageable);
    }

    @GetMapping("/{id}")
    public ApplicationDetailResponse getApplicationDetail(@PathVariable Long id) {
        return queryApplicationDetailService.execute(id);
    }

    @GetMapping("/my")
    public ApplicationListResponse getMyApplication() {
        return queryMyApplicationService.execute();
    }
}
