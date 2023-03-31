package com.soogung.simblue.infrastructure.s3.validation;

import com.soogung.simblue.infrastructure.s3.exception.EmptyFileException;
import com.soogung.simblue.infrastructure.s3.exception.TooLongNameException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@FunctionalInterface
public interface FileValidator {
    void customValidate(MultipartFile file);

    default void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw EmptyFileException.EXCEPTION;
        }

        if (Objects.nonNull(file.getOriginalFilename()) && file.getOriginalFilename().length() > 20) {
            throw TooLongNameException.EXCEPTION;
        }

        customValidate(file);
    }
}
