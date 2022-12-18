package com.soogung.simblue.domain.application.facade;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.repository.ApplicationQuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRepository;
import com.soogung.simblue.domain.application.exception.ApplicationNotFoundException;
import com.soogung.simblue.domain.application.exception.ApplicationQuestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplicationFacade {

    private final ApplicationRepository applicationRepository;
    private final ApplicationQuestionRepository applicationQuestionRepository;

    @Transactional(readOnly = true)
    public Application findApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public ApplicationQuestion findApplicationQuestionById(Long id) {
        return applicationQuestionRepository.findById(id)
                .orElseThrow(() -> ApplicationQuestionNotFoundException.EXCEPTION);
    }
}
