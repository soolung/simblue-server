package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.StudentRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import com.soogung.simblue.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JoinStudentService joinStudentService;
    private final JoinTeacherService joinTeacherService;
    private final UpdateStudentService updateStudentService;
    private final UpdateTeacherService updateTeacherService;
    private final DeleteUserService deleteUserService;

    @PostMapping("/student")
    public void joinStudent(@RequestBody @Valid StudentRequest request) {
        joinStudentService.execute(request);
    }

    @PostMapping("/teacher")
    public void joinTeacher(@RequestBody @Valid TeacherRequest request) {
        joinTeacherService.execute(request);
    }

    @PutMapping("/student")
    public void updateStudent(@RequestBody @Valid StudentRequest request) {
        updateStudentService.execute(request);
    }

    @PutMapping("/teacher")
    public void updateTeacher(@RequestBody @Valid TeacherRequest request) {
        updateTeacherService.execute(request);
    }

    @DeleteMapping
    public void deleteUser() {
        deleteUserService.execute();
    }
}
