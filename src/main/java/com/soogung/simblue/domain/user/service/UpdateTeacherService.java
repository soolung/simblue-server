package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTeacherService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(TeacherRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateInformation(request.getName(), passwordEncoder.encode(request.getPassword()));
    }
}
