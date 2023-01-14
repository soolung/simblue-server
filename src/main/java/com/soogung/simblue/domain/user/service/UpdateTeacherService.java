package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UpdateTeacherService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateTeacherRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateName(request.getName());
    }
}
