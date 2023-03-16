package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.presentation.dto.response.BannerImageResponse;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadBannerImageService {

    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public BannerImageResponse execute(MultipartFile image) {
        userFacade.getCurrentTeacher();

        return new BannerImageResponse(
                s3Service.uploadImage(image, "banner"));
    }
}
