package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.JoinStudentRequest;
import com.soogung.simblue.domain.user.presentation.dto.request.JoinTeacherRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.service.GetGoogleAuthLinkService;
import com.soogung.simblue.domain.user.service.JoinStudentService;
import com.soogung.simblue.domain.user.service.JoinTeacherService;
import com.soogung.simblue.domain.user.service.JoinWithGoogleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final GetGoogleAuthLinkService getGoogleAuthLinkService;
    private final JoinWithGoogleService joinWithGoogleService;
    private final JoinStudentService joinStudentService;
    private final JoinTeacherService joinTeacherService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return getGoogleAuthLinkService.execute();
    }

    @GetMapping("/google/callback")
    public TokenResponse joinWithGoogle(@RequestParam String code) {
        return joinWithGoogleService.execute(code);
    }

    @PostMapping("/student")
    public void joinStudent(@RequestBody @Valid JoinStudentRequest request) {
        joinStudentService.execute(request);
    }

    @PostMapping("/teacher")
    public void joinTeacher(@RequestBody @Valid JoinTeacherRequest request) {
        joinTeacherService.execute(request);
    }
}
