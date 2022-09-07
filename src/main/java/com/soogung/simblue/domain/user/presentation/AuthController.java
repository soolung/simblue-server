package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.service.GoogleAuthLinkService;
import com.soogung.simblue.domain.user.service.GoogleAuthService;
import com.soogung.simblue.domain.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;
    private final LoginService loginService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @GetMapping("/google/callback")
    public TokenResponse authGoogle(@RequestParam String code) {
        return googleAuthService.execute(code);
    }

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }
}