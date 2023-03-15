package com.soogung.simblue.domain.user.facade;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.StudentRepository;
import com.soogung.simblue.domain.user.domain.repository.TeacherRepository;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.domain.user.exception.UserAlreadyExistsException;
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
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public void validateExistsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }

    @Transactional(readOnly = true)
    public Teacher getCurrentTeacher() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return teacherRepository.findByUser(auth.getUser())
                .orElseThrow(() -> AuthorityMismatchException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Student getCurrentStudent() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentRepository.findByUser(auth.getUser())
                .orElseThrow(() -> AuthorityMismatchException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Student findStudentByUser(User user) {
        return studentRepository.findByUser(user)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Teacher findTeacherByUser(User user) {
        return teacherRepository.findByUser(user)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
