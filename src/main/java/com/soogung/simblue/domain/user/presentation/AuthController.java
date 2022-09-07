package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.service.GetGoogleAuthLinkService;
import com.soogung.simblue.domain.user.service.JoinWithGoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GetGoogleAuthLinkService getGoogleAuthLinkService;
    private final JoinWithGoogleService joinWithGoogleService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return getGoogleAuthLinkService.execute();
    }

    @GetMapping("/google/callback")
    public TokenResponse joinWithGoogle(@RequestParam String code) {
        return joinWithGoogleService.execute(code);
    }
}