package com.soogung.simblue.domain.banner.presentation.dto.response;

import com.soogung.simblue.domain.banner.domain.Banner;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BannerResponse {

    private String imageUri;
    private String linkTo;

    public static BannerResponse of(Banner banner) {
        return BannerResponse.builder()
                .imageUri(banner.getImageUri())
                .linkTo(banner.getLinkTo())
                .build();
    }
}
