package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.service.GoogleAuthLinkService;
import com.soogung.simblue.domain.user.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @GetMapping("/google/callback")
    public TokenResponse authGoogle(@RequestParam String code) {
        return googleAuthService.execute(code);
    }

    @PostMapping
    public TokenResponse loginWithGoogle(@RequestParam String code) {

    }
}