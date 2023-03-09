package com.soogung.simblue.domain.banner.presentation.dto.response;

import com.soogung.simblue.domain.banner.domain.Banner;
import lombok.Builder;

@Builder
public class BannerResponse {

    private String imageUri;
    private String linkTo;

    public static BannerResponse of(Banner banner) {
        Banner.builder()
                .imageUri(banner.getImageUri())
                .linkTo(banner.getLinkTo())
                .build();
    }
}
