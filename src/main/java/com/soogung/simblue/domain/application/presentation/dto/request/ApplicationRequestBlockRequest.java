package com.soogung.simblue.domain.application.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestBlockRequest {

    @Valid
    List<ApplicationRequestRequest> applicationRequestRequests;
}
