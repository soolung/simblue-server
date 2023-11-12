package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import com.soogung.simblue.domain.user.service.JoinTeacherService;
import com.soogung.simblue.global.auth.annotation.AuthenticationPrincipal;
import com.soogung.simblue.global.auth.annotation.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final JoinTeacherService joinTeacherService;

    @PostMapping
    public void joinTeacher(
            @AuthenticationPrincipal(authority = Authority.TEACHER) User user,
            @RequestBody @Valid TeacherRequest request) {
        joinTeacherService.execute(user, request);
    }

}
