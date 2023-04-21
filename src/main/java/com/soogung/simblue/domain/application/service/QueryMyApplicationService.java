package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.application.domain.type.Status;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationStatusResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.MyApplicationResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
                    getStudentApplication(user),
                    user.getAuthority()
            );
        }

        if (user.getAuthority() == Authority.ROLE_TEACHER) {
            return new ApplicationStatusResponse(
                    getTeacherApplication(user),
                    user.getAuthority()
            );
        }

        throw AuthorityMismatchException.EXCEPTION;
    }

    private HashMap<String, List<MyApplicationResponse>> getStudentApplication(User student) {
        HashMap<String, List<MyApplicationResponse>> result = new HashMap<>();
        result.put("applicationList",
                replyBlockRepository.findAllByStudent(student)
                        .stream().map(MyApplicationResponse::of)
                        .collect(Collectors.toList())
        );

        return result;
    }

    private HashMap<String, List<MyApplicationResponse>> getTeacherApplication(User user) {
        HashMap<String, List<MyApplicationResponse>> result = new HashMap<>();
        Arrays.stream(Status.values())
                .forEach(s -> result.put(s.name(), new ArrayList<>()));

        ownerRepository.findAllByUser(user)
                .stream().map(Owner::getApplication)
                .forEach(a -> result.get(a.getStatus().name())
                        .add(MyApplicationResponse.of(a, replyBlockRepository.countByApplication(a))));

        return result;
    }
}