package com.soogung.simblue.domain.banner.presentation;

import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerImageResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.MyBannerListResponse;
import com.soogung.simblue.domain.banner.service.*;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import com.soogung.simblue.global.auth.annotation.Authority;
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
    public MyBannerListResponse getMyBannerList(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user

    ) {
        return queryMyBannerService.execute(user);
    }

    @PostMapping
    public void registerBanner(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestBody @Valid BannerRequest request
    ) {
        registerBannerService.execute(user, request);
    }

    @PostMapping("/image")
    public BannerImageResponse uploadBannerImage(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestPart(value = "image") MultipartFile image
    ) {
        return uploadBannerImageService.execute(image);
    }

    @PutMapping("/{id}")
    public void updateBanner(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @PathVariable Long id,
            @RequestBody @Valid BannerRequest request
    ) {
        updateBannerService.execute(user, id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBanner(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @PathVariable Long id
    ) {
        deleteBannerService.execute(user, id);
    }
}
