package com.soogung.simblue.domain.auth.presentation;

import com.soogung.simblue.domain.auth.presentation.dto.RefreshTokenRequestBodyDto;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.auth.service.*;
import com.soogung.simblue.global.feign.auth.dto.response.BsmTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;
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
    public BsmTokenResponse userSignup(HttpServletRequest request) throws IOException {
        return loginService.execute(request.getHeader("authCode"));
    }

    @PutMapping("/refresh/access")
    public BsmTokenResponse refreshAccessToken(@RequestBody RefreshTokenRequestBodyDto request){
        return refreshTokenService.execute(request.getRefresh_token());
    }
}
