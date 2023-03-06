package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.type.Status;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationStatusResponse;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryMyApplicationService {

    private final OwnerRepository ownerRepository;
    private final ReplyBlockRepository replyBlockRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public ApplicationStatusResponse execute() {
        User user = userFacade.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT) {
            return new ApplicationStatusResponse(
                    getStudentApplication(userFacade.findStudentByUser(user)),
                    user.getAuthority()
            );
        }

        if (user.getAuthority() == Authority.ROLE_TEACHER) {
            return new ApplicationStatusResponse(
                    getTeacherApplication(userFacade.findTeacherByUser(user)),
                    user.getAuthority()
            );
        }

        throw AuthorityMismatchException.EXCEPTION;
    }

    private HashMap<String, List<ApplicationResponse>> getStudentApplication(Student student) {
        HashMap<String, List<ApplicationResponse>> result = new HashMap<>();
        result.put("applicationList",
                replyBlockRepository.findAllByStudent(student)
                        .stream().map(ApplicationResponse::of)
                        .collect(Collectors.toList())
        );

        return result;
    }

    private HashMap<String, List<ApplicationResponse>> getTeacherApplication(Teacher teacher) {
        HashMap<String, List<ApplicationResponse>> result = new HashMap<>();
        Arrays.stream(kanbanStatus.values())
                .forEach(s -> result.put(s.name(), new ArrayList<>()));

        ownerRepository.findAllByTeacher(teacher)
                .stream().map(o -> ApplicationResponse.of(o.getApplication()))
                .forEach(a -> {
                    LocalDate now = LocalDate.now();

                    if (a.getStatus() == Status.ALWAYS) {
                        result.get(kanbanStatus.ALWAYS.name()).add(a);
                    }

                    else if (a.getStatus() == Status.CLOSED || a.getEndDate().isBefore(now)) {
                        result.get(kanbanStatus.DONE.name()).add(a);
                    }

                    else if (a.getStartDate().isAfter(now)) {
                        result.get(kanbanStatus.NOT_STARTED.name()).add(a);
                    }

                    else {
                        result.get(kanbanStatus.IN_PROGRESS.name()).add(a);
                    }
                });

        return result;
    }
}

enum kanbanStatus {
    ALWAYS, NOT_STARTED, IN_PROGRESS, DONE
}