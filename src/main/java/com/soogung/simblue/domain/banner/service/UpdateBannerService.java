package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.facade.BannerFacade;
import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UpdateBannerService {

    private final BannerFacade bannerFacade;

    @Transactional
    public void execute(User user, Long id, @Valid BannerRequest request) {
        Banner banner = bannerFacade.getBanner(id);
        banner.validatePermission(user);

        banner.update(
                request.getImageUri(),
                request.getLinkTo(),
                request.getEndDate()
        );
    }
}
