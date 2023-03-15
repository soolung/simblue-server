package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.domain.type.Status;
import com.soogung.simblue.domain.banner.facade.BannerFacade;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerListResponse;
import com.soogung.simblue.domain.banner.presentation.dto.response.BannerResponse;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeleteBannerService {

    private final UserFacade userFacade;
    private final BannerFacade bannerFacade;

    @Transactional
    public void execute(Long id) {
        Teacher teacher = userFacade.getCurrentTeacher();
        Banner banner = bannerFacade.getBanner(id);
        banner.validatePermission(teacher);
        banner.delete();
    }
}
