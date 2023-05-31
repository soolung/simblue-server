package com.soogung.simblue.domain.auth.presentation;

import com.soogung.simblue.domain.auth.domain.type.AuthType;
import com.soogung.simblue.domain.auth.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.auth.presentation.dto.response.AccessTokenResponse;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.auth.service.GoogleAuthLinkService;
import com.soogung.simblue.domain.auth.service.GoogleAuthService;
import com.soogung.simblue.domain.auth.service.LoginService;
import com.soogung.simblue.domain.auth.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/google")
    public String getGoogleAuthLink(@RequestParam AuthType type) {
        return googleAuthLinkService.execute(type);
    }

    @PostMapping("/google/callback")
    public TokenResponse authGoogle(@RequestParam String code, @RequestParam AuthType type) {
        return googleAuthService.execute(code, type);
    }

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @PutMapping
    public AccessTokenResponse refreshToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        return refreshTokenService.execute(refreshToken);
    }
}