package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.repository.BannerRepository;
import com.soogung.simblue.domain.banner.presentation.dto.request.BannerRequest;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RegisterBannerService {

    private final UserFacade userFacade;
    private final BannerRepository bannerRepository;
    private final S3Service s3Service;

    @Transactional
    public void execute(BannerRequest request, MultipartFile image) {
        Teacher teacher = userFacade.findTeacherByUser(userFacade.getCurrentUser());
        String imageUri = s3Service.uploadImage(image, "banner");

        bannerRepository.save(
                Banner.builder()
                        .endDate(request.getEndDate())
                        .imageUri(imageUri)
                        .linkTo(request.getLinkTo())
                        .teacher(teacher)
                        .build()
        );
    }
}
