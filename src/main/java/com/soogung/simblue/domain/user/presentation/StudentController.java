package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.StudentRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateStudentRequest;
import com.soogung.simblue.domain.user.service.JoinStudentService;
import com.soogung.simblue.domain.user.service.UpdateStudentService;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import com.soogung.simblue.global.auth.annotation.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/student")
@RequiredArgsConstructor
public class StudentController {

    private final JoinStudentService joinStudentService;
    private final UpdateStudentService updateStudentService;

    @PostMapping
    public void joinStudent(
            @AuthenticationPrincipal(authority = Authority.STUDENT) User user,
            @RequestBody @Valid StudentRequest request
    ) {
        joinStudentService.execute(user, request);
    }

    @PutMapping
    public void updateStudent(
            @AuthenticationPrincipal(authority = Authority.STUDENT) User user,
            @RequestBody @Valid UpdateStudentRequest request
    ) {
        updateStudentService.execute(user, request);
    }
}
