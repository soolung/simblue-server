package com.soogung.simblue.domain.banner.service;

import com.soogung.simblue.domain.banner.presentation.dto.response.BannerImageResponse;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.infrastructure.s3.exception.FailedToSaveException;
import com.soogung.simblue.infrastructure.s3.exception.ImageSizeMismatchException;
import com.soogung.simblue.infrastructure.s3.service.S3Service;
import com.soogung.simblue.infrastructure.s3.validation.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploadBannerImageService {

    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public BannerImageResponse execute(MultipartFile image) {
        userFacade.getCurrentTeacher();

        return new BannerImageResponse(
                s3Service.uploadImage(image, "banner", file -> {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                        if (!(bufferedImage.getWidth() == 1400 && bufferedImage.getHeight() == 450)) {
                            throw ImageSizeMismatchException.EXCEPTION;
                        }
                    } catch (IOException e) {
                        throw FailedToSaveException.EXCEPTION;
                    }
                })
        );
    }
}
