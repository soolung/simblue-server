package com.soogung.simblue.global.config;

import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web
                        .ignoring()
                        .mvcMatchers("/swagger-ui/**", "/configuration/**", "/swagger-resources/**", "/v3/api-docs", "/webjars/**", "/webjars/springfox-swagger-ui/*.{js,css}");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // auth
                .antMatchers("/auth/**").permitAll()

                // application
                .antMatchers(HttpMethod.GET, "/application/my").authenticated()
                .antMatchers(HttpMethod.PUT, "/application/{\\d+}").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.DELETE, "/application/{\\d+}").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.GET, "/application/{\\d+}/form").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.GET, "/application/{\\d+}/result").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.PUT, "/application/{\\d+}/close").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.POST, "/application").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.GET, "/application/**").permitAll()

                // notice
                .antMatchers(HttpMethod.POST, "/notice").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.PUT, "/notice/{\\d+}").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.PUT, "/notice/{\\d+}/pinned").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.DELETE, "/notice/{\\d+}").hasRole(Authority.ROLE_TEACHER.getRole())

                // reply
                .antMatchers(HttpMethod.GET, "/reply/{\\d+}").authenticated()
                .antMatchers(HttpMethod.GET, "/reply/assigned").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.POST, "/reply").authenticated()
                .antMatchers(HttpMethod.PUT, "/reply/{\\d+}").authenticated()
                .antMatchers(HttpMethod.PUT, "/reply/{\\d+}/handle").hasRole(Authority.ROLE_TEACHER.getRole())
                .antMatchers(HttpMethod.DELETE, "/reply/{\\d+}").authenticated()
                .antMatchers(HttpMethod.PUT, "/reply/{\\d+}/handle").hasRole(Authority.ROLE_TEACHER.getRole())

                // banner
                .antMatchers(HttpMethod.GET, "/banner").permitAll()

                .anyRequest().authenticated();

        return http.build();
    }
}
