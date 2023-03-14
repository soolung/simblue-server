package com.soogung.simblue.domain.banner.presentation;

import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.service.DeleteBannerService;
import com.soogung.simblue.domain.banner.service.QueryBannerService;
import com.soogung.simblue.domain.banner.service.RegisterBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {

    private final QueryBannerService queryBannerService;
    private final RegisterBannerService registerBannerService;
    private final DeleteBannerService deleteBannerService;

    @GetMapping
    public BannerListResponse getBannerList() {
        return queryBannerService.execute();
    }

    @PostMapping
    public void registerBanner(
            @RequestPart(value = "data") @Valid BannerRequest request,
            @RequestPart(value = "image") MultipartFile image
    ) {
        registerBannerService.execute(request, image);
    }

    @DeleteMapping("/{id}")
    public void deleteBanner(@PathVariable Long id) {
        deleteBannerService.execute(id);
    }
}
