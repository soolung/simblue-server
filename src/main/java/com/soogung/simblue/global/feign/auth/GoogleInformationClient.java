package com.soogung.simblue.global.feign.auth;

import com.soogung.simblue.global.feign.auth.dto.response.GoogleInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInformationClient {

    @GetMapping("?alt=json&access_token={TOKEN}")
    public GoogleInformationResponse getUserInformation(@PathVariable("TOKEN") String accessToken);
}