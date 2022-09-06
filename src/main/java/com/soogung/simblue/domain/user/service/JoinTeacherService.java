package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.repository.TeacherRepository;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinTeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public void execute(JoinTeacherRequest request) {

    }
}
