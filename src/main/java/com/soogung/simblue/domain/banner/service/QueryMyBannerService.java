package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerDetailResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.MyBannerListResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryMyBannerService {

    private final UserFacade userFacade;
    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public MyBannerListResponse execute() {
        User user = userFacade.getCurrentUser();

        return new MyBannerListResponse(
                bannerRepository.findByUser(user).stream()
                        .map(BannerDetailResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
