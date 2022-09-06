package com.soogung.simblue.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.soogung.simblue.global.feign")
public class FeignConfig {
}