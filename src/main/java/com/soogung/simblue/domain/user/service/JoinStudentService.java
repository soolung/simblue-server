package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.repository.StudentRepository;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinStudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinStudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public void execute(JoinStudentRequest request) {

    }
}
