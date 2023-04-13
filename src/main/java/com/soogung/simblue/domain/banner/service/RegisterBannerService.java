package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterBannerService {

    private final UserFacade userFacade;
    private final BannerRepository bannerRepository;

    @Transactional
    public void execute(BannerRequest request) {
        User teacher = userFacade.getCurrentUser();

        bannerRepository.save(
                Banner.builder()
                        .endDate(request.getEndDate())
                        .imageUri(request.getImageUri())
                        .linkTo(request.getLinkTo())
                        .teacher(teacher)
                        .build()
        );
    }
}
