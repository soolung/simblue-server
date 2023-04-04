package com.soogung.simblue.domain.auth.presentation;

import com.soogung.simblue.domain.auth.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.auth.presentation.dto.response.AccessTokenResponse;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.auth.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;
    private final BsmAuthService bsmAuthService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @PostMapping("/google/callback")
    public TokenResponse authGoogle(@RequestParam String code) {
        return googleAuthService.execute(code);
    }

    @PostMapping("/bsm")
    public TokenResponse userSignup(HttpServletRequest request) throws IOException {
        return loginService.execute(request.getHeader("authCode"));
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
