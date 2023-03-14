package com.soogung.simblue.domain.banner.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BannerListResponse {

    private List<BannerResponse> bannerList;
}
