package com.soogung.simblue.global.feign.auth;

import com.soogung.simblue.global.feign.auth.dto.request.GoogleAuthRequest;
import com.soogung.simblue.global.feign.auth.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuthClient {

    @PostMapping
    public TokenResponse getAccessToken(GoogleAuthRequest request);
}