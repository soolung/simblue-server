package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.facade.BannerFacade;
import com.soogung.simblue.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteBannerService {

    private final BannerFacade bannerFacade;

    @Transactional
    public void execute(User user, Long id) {
        Banner banner = bannerFacade.getBanner(id);
        banner.validatePermission(user);
        banner.delete();
    }
}
