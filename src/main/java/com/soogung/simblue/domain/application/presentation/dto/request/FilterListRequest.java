package com.soogung.simblue.domain.application.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterListRequest {

    @Nullable
    @Valid
    private List<FilterRequest> filterList;
}
