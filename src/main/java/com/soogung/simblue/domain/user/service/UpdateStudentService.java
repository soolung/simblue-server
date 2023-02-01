package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateStudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UpdateStudentService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateStudentRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateName(request.getName());

        Student student = userFacade.findStudentByUser(user);
        student.updateInformation(request.getStudentNumber(), request.getAdmissionYear());
    }
}
