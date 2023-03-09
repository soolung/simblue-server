package com.soogung.simblue.domain.banner.presentation;

import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.service.QueryBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BannerController {

    private final QueryBannerService queryBannerService;

    @GetMapping
    public BannerListResponse getBannerList() {
        return queryBannerService.execute();
    }
}
