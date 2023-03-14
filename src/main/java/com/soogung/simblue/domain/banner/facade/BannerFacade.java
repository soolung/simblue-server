package com.soogung.simblue.domain.banner.facade;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.exception.BannerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BannerFacade {

    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public Banner getBanner(Long id) {
        return bannerRepository.findBannerById(id)
                .orElseThrow(() -> BannerNotFoundException.EXCEPTION);
    }
}
