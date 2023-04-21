package com.soogung.simblue.domain.user.facade;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.exception.UserNotFoundException;
import com.soogung.simblue.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }

    @Transactional(readOnly = true)
    public String getName(Long userId) {
        User user = getUserById(userId);
        if (user.getAuthority() == Authority.ROLE_TEACHER) {
            return user.getName() + " 선생님";
        }

        return user.getStudentNumber() + " " + user.getName();
    }
}
