package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.TeacherRepository;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinTeacherService {

    private final TeacherRepository teacherRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(JoinTeacherRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateInformation(request.getName(), passwordEncoder.encode(user.getPassword()));

        teacherRepository.save(
                Teacher.builder()
                        .user(user)
                        .build()
        );
    }
}
