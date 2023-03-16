package com.soogung.simblue.domain.banner.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BannerRequest {

    @NotBlank
    @URL
    private String imageUri;

    @Nullable
    private String linkTo;

    @NotNull
    private LocalDate endDate;
}
