package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.JoinStudentRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinTeacherRequest;
import com.soogung.simblue.domain.user.service.DeleteUserService;
import com.soogung.simblue.domain.user.service.JoinStudentService;
import com.soogung.simblue.domain.user.service.JoinTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JoinStudentService joinStudentService;
    private final JoinTeacherService joinTeacherService;
    private final DeleteUserService deleteUserService;

    @PostMapping("/student")
    public void joinStudent(@RequestBody @Valid JoinStudentRequest request) {
        joinStudentService.execute(request);
    }

    @PostMapping("/teacher")
    public void joinTeacher(@RequestBody @Valid JoinTeacherRequest request) {
        joinTeacherService.execute(request);
    }

    @DeleteMapping
    public void deleteUser() {
        deleteUserService.execute();
    }
}
