package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.StudentRepository;
import com.soogung.simblue.domain.user.domain.repository.TeacherRepository;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();
        if (user.getAuthority() == Authority.ROLE_STUDENT) {
            studentRepository.delete(userFacade.findStudentByUser(user));
        } else if (user.getAuthority() == Authority.ROLE_TEACHER) {
            teacherRepository.delete(userFacade.findTeacherByUser(user));
        }
    }
}
