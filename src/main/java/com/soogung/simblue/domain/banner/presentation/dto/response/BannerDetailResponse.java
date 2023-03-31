package com.soogung.simblue.domain.banner.presentation.dto.response;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.type.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BannerDetailResponse {

    private Long id;
    private String imageUri;
    private String linkTo;
    private LocalDate endDate;
    private Status status;

    public static BannerDetailResponse of(Banner banner) {
        return BannerDetailResponse.builder()
                .id(banner.getId())
                .imageUri(banner.getImageUri())
                .linkTo(banner.getLinkTo())
                .endDate(banner.getEndDate())
                .status(banner.getStatus())
                .build();
    }
}
