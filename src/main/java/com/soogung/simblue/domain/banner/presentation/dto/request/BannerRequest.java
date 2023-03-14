package com.soogung.simblue.domain.banner.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BannerRequest {

    @Nullable
    private String linkTo;

    @NotNull
    private LocalDate endDate;
}
