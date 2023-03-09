package com.soogung.simblue.domain.banner.presentation.dto.response;

import com.soogung.simblue.domain.banner.domain.Banner;
import lombok.Builder;

@Builder
public class BannerResponse {

    private Long id;
    private String imageUri;
    private String linkTo;

    public static BannerResponse of(Banner banner) {
        return BannerResponse.builder()
                .id(banner.getId())
                .imageUri(banner.getImageUri())
                .linkTo(banner.getLinkTo())
                .build();
    }
}
