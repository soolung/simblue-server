package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTeacherService {

    @Transactional
    public void execute(User user, UpdateTeacherRequest request) {
        user.updateName(request.getName());
    }
}
