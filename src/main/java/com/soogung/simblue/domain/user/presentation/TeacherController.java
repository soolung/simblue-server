package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdateTeacherRequest;
import com.soogung.simblue.domain.user.service.JoinTeacherService;
import com.soogung.simblue.domain.user.service.UpdateTeacherService;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import com.soogung.simblue.global.auth.annotation.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final JoinTeacherService joinTeacherService;
    private final UpdateTeacherService updateTeacherService;

    @PostMapping
    public void joinTeacher(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestBody @Valid TeacherRequest request) {
        joinTeacherService.execute(user, request);
    }

    @PutMapping
    public void updateTeacher(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestBody @Valid UpdateTeacherRequest request
    ) {
        updateTeacherService.execute(user, request);
    }
}
