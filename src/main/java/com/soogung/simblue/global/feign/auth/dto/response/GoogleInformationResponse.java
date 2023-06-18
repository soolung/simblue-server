package com.soogung.simblue.global.feign.auth.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleInformationResponse {

    private String email;
    private String name;
}
