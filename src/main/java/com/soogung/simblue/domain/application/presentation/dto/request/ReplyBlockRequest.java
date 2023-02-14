package com.soogung.simblue.domain.application.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyBlockRequest {

    @NotNull
    private Long applicationId;

    @Valid
    private List<ReplyRequest> requestRequestList;
}
