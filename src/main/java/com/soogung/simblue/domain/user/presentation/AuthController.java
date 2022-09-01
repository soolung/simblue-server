package com.soogung.simblue.domain.user.presentation;

import com.soogung.simblue.domain.user.presentation.dto.request.AuthRequest;
import com.soogung.simblue.domain.user.service.GetGoogleAuthLinkService;
import com.soogung.simblue.domain.user.service.JoinWithGoogleService;
import com.soogung.simblue.global.feign.auth.dto.response.TokenResponse;
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

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return getGoogleAuthLinkService.execute();
    }

    @PostMapping("/google")
    public TokenResponse joinWithGoogle(@RequestBody @Valid AuthRequest request) {
        return joinWithGoogleService.execute(request);
    }
}
