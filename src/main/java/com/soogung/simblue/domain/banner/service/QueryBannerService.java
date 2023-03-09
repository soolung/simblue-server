package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.domain.type.Status;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryBannerService {

    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public BannerListResponse execute() {
        return new BannerListResponse(
                bannerRepository.findByStatus(Status.ACTIVE)
                        .stream()
                        .map(BannerResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
