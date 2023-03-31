package com.soogung.simblue.infrastructure.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.soogung.simblue.infrastructure.s3.config.properties.S3Properties;
import com.soogung.simblue.infrastructure.s3.exception.FailedToSaveException;
import com.soogung.simblue.infrastructure.s3.validation.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Properties s3Properties;
    private final AmazonS3Client amazonS3Client;

    public String uploadImage(MultipartFile image, String folder, FileValidator validator) {
        validator.validate(image);
        String fileName = createFileName(folder, image.getOriginalFilename());

        try {
            PutObjectRequest request = new PutObjectRequest(
                    s3Properties.getBucket(),
                    fileName,
                    image.getInputStream(),
                    getObjectMetadata(image)
            );
            amazonS3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            e.printStackTrace();
            throw FailedToSaveException.EXCEPTION;
        }

        return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private String createFileName(String folder, String originalName) {
        return folder + "/" + UUID.randomUUID() + "-" + originalName;
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        return objectMetadata;
    }
}
