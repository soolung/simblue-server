package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateStudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateStudentService {

    @Transactional
    public void execute(User user, UpdateStudentRequest request) {
        user.updateStudentInformation(request.getStudentNumber(), request.getAdmissionYear());
    }
}
