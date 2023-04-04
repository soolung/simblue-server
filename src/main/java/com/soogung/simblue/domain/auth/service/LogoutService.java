package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutService {

    private final JwtUtil jwtUtil;
}
