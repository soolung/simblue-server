package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import com.soogung.simblue.domain.application.domain.repository.ApplicationOwnerRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationQuestionRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestBlockRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationRequestResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResultResponse;
import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationUserResponseResponse;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryApplicationResultService {

    private final UserFacade userFacade;
    private final ApplicationOwnerRepository applicationOwnerRepository;
    private final ApplicationFacade applicationFacade;
    private final ApplicationQuestionRepository applicationQuestionRepository;
    private final ApplicationRequestBlockRepository applicationRequestBlockRepository;

    @Transactional(readOnly = true)
    public ApplicationResultResponse execute(Long id) {
        Teacher teacher = userFacade.findTeacherByUser(userFacade.getCurrentUser());
        checkPermission(id, teacher.getId());


        List<String> questionList = applicationQuestionRepository.findByApplicationIdOrderById(id).stream()
                .map(ApplicationQuestion::getQuestion)
                .collect(Collectors.toList());

        List<ApplicationUserResponseResponse> userResponseList = applicationRequestBlockRepository
                .findApplicationResult(id).stream().distinct()
                .map(this::createApplicationUserResponseResponse)
                .collect(Collectors.toList());


        return new ApplicationResultResponse(
                questionList,
                userResponseList
        );
    }

    private void checkPermission(Long applicationId, Long teacherId) {
        if (!applicationOwnerRepository.existsByApplicationIdAndTeacherId(applicationId, teacherId)) {
            throw AuthorityMismatchException.EXCEPTION;
        }
    }

    private ApplicationUserResponseResponse createApplicationUserResponseResponse(ApplicationRequestBlock block) {
        Student student = block.getStudent();
        Map<Long, List<ApplicationRequestResponse>> answerList = block.getRequests().stream()
                .map(ApplicationRequestResponse::of)
                .collect(Collectors.groupingBy(ApplicationRequestResponse::getQuestionId));


        return new ApplicationUserResponseResponse(
                student.getUser().getName(),
                student.getStudentNumber(),
                answerList
        );
    }
}
