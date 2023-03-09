package com.soogung.simblue.domain.banner.presentation;

import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.service.DeleteBannerService;
import com.soogung.simblue.domain.banner.service.QueryBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {

    private final QueryBannerService queryBannerService;
    private final DeleteBannerService deleteBannerService;

    @GetMapping
    public BannerListResponse getBannerList() {
        return queryBannerService.execute();
    }

    @DeleteMapping("/{id}")
    public void deleteBanner(@PathVariable Long id) {
        deleteBannerService.execute(id);
    }
}
