package com.soogung.simblue.global.auth.annotation;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.global.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationExtractor authenticationExtractor;
    private final TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = authenticationExtractor.extract(webRequest);
        User user = tokenService.getUser(token);
        validateAuthority(user, Objects.requireNonNull(parameter.getParameterAnnotation(AuthenticationPrincipal.class)));

        return user;
    }

    private void validateAuthority(User user, AuthenticationPrincipal authenticationPrincipal) {
        if (
                !authenticationPrincipal.authority().equals(Authority.ALL) &&
                !user.getAuthority().getRole().equals(authenticationPrincipal.authority().name())
        ) {
            throw AuthorityMismatchException.EXCEPTION;
        }
    }
}