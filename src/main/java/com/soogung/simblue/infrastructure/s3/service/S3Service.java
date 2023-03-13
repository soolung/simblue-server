package com.soogung.simblue.infrastructure.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.soogung.simblue.infrastructure.s3.config.properties.S3Properties;
import com.soogung.simblue.infrastructure.s3.exception.EmptyFileException;
import com.soogung.simblue.infrastructure.s3.exception.FailedToSaveException;
import com.soogung.simblue.infrastructure.s3.exception.TooLongNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Service {

    private final S3Properties s3Properties;
    private final AmazonS3Client amazonS3Client;

    public String uploadImage(MultipartFile image) {
        validateFile(image);
        String fileName = createFileName(image.getOriginalFilename());

        try {
            PutObjectRequest request = new PutObjectRequest(
                    s3Properties.getBucket(),
                    fileName,
                    image.getInputStream(),
                    getObjectMetadata(image)
            );
            amazonS3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw FailedToSaveException.EXCEPTION;
        }

        return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw EmptyFileException.EXCEPTION;
        }

        if (file.getOriginalFilename().length() > 20) {
            throw TooLongNameException.EXCEPTION;
        }
    }

    private String createFileName(String originalName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + "-" + originalName;
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        return objectMetadata;
    }
}
