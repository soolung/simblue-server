package com.soogung.simblue.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soogung.simblue.global.error.filter.GlobalErrorFilter;
import com.soogung.simblue.global.security.auth.AuthDetailsService;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import com.soogung.simblue.global.security.jwt.JwtValidateService;
import com.soogung.simblue.global.security.jwt.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthDetailsService authDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;
    private final ObjectMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .mvcMatchers("/swagger-ui/**", "/configuration/**", "/swagger-resources/**", "/v3/api-docs","/webjars/**", "/webjars/springfox-swagger-ui/*.{js,css}");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/application/my").authenticated()
                .antMatchers(HttpMethod.GET, "/application/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();

        http
                .addFilterBefore(new JwtAuthenticationFilter(authDetailsService, jwtTokenProvider, jwtValidateService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GlobalErrorFilter(mapper), JwtAuthenticationFilter.class);
    }
}
