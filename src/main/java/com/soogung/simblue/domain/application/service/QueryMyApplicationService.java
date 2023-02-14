package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationListResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryMyApplicationService {

    private final OwnerRepository ownerRepository;
    private final ReplyBlockRepository replyBlockRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public ApplicationListResponse execute() {
        User user = userFacade.getCurrentUser();

        return user.getAuthority() == Authority.ROLE_STUDENT ?
                getStudentApplication(userFacade.findStudentByUser(user)) :
                getTeacherApplication(userFacade.findTeacherByUser(user));
    }

    private ApplicationListResponse getStudentApplication(Student student) {
        return new ApplicationListResponse(
                replyBlockRepository.findAllByStudent(student)
                        .stream().map(b -> ApplicationResponse.of(b.getApplication()))
                        .collect(Collectors.toList())
        );
    }

    private ApplicationListResponse getTeacherApplication(Teacher teacher) {
        return new ApplicationListResponse(
                ownerRepository.findAllByTeacher(teacher)
                        .stream().map(o -> ApplicationResponse.of(o.getApplication()))
                        .collect(Collectors.toList())
        );
    }
}
