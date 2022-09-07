package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.StudentRepository;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinStudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinStudentService {

    private final StudentRepository studentRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(JoinStudentRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateInformation(request.getName(), passwordEncoder.encode(request.getPassword());

        studentRepository.save(
                Student.builder()
                        .admissionYear(request.getAdmissionYear())
                        .studentNumber(request.getStudentNumber())
                        .user(user)
                        .build()
        );
    }
}
