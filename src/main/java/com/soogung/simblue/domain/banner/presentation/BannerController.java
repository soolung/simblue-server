package com.soogung.simblue.domain.banner.presentation;

import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerImageResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.MyBannerListResponse;
import com.soogung.simblue.domain.banner.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {

    private final QueryBannerService queryBannerService;

    private final QueryMyBannerService queryMyBannerService;
    private final RegisterBannerService registerBannerService;
    private final UploadBannerImageService uploadBannerImageService;
    private final UpdateBannerService updateBannerService;
    private final DeleteBannerService deleteBannerService;

    @GetMapping
    public BannerListResponse getBannerList() {
        return queryBannerService.execute();
    }

    @GetMapping("/my")
    public MyBannerListResponse getMyBannerList() {
        return queryMyBannerService.execute();
    }

    @PostMapping
    public void registerBanner(
            @RequestBody @Valid BannerRequest request
    ) {
        registerBannerService.execute(request);
    }

    @PostMapping("/image")
    public BannerImageResponse uploadBannerImage(
            @RequestPart(value = "image") MultipartFile image
    ) {
        return uploadBannerImageService.execute(image);
    }

    @PutMapping("/{id}")
    public void updateBanner(
            @PathVariable Long id,
            @RequestBody @Valid BannerRequest request
    ) {
        updateBannerService.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBanner(@PathVariable Long id) {
        deleteBannerService.execute(id);
    }
}
