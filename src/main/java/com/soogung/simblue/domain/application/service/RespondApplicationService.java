package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationRequest;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestRepository;
import com.soogung.simblue.domain.application.exception.AlreadyRespondException;
import com.soogung.simblue.domain.application.exception.ApplicationHasAlreadyEndedException;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequestBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequestRequest;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespondApplicationService {

    private final ApplicationFacade applicationFacade;
    private final UserFacade userFacade;
    private final ApplicationRequestBlockRepository applicationRequestBlockRepository;
    private final ApplicationRequestRepository applicationRequestRepository;

    @Transactional
    public void execute(Long id, ApplicationRequestBlockRequest request) {
        Application application = applicationFacade.findApplicationById(id);
        Student student = userFacade.findStudentByUser(userFacade.getCurrentUser());
        validateApplicationPeriod(application);
        validateFirstResponse(application, student);

        ApplicationRequestBlock block = applicationRequestBlockRepository.save(createApplicationRequestBlock(application, student));
        applicationRequestRepository.saveAll(
                toApplicationRequestsFromRequest(
                        request.getRequestRequestList(), block));
    }

    private void validateApplicationPeriod(Application application) {
        if (LocalDate.now().isAfter(application.getEndDate())) {
            throw ApplicationHasAlreadyEndedException.EXCEPTION;
        }
    }

    private void validateFirstResponse(Application application, Student student) {
        if (applicationRequestBlockRepository.existsByApplicationAndStudent(application, student)) {
            throw AlreadyRespondException.EXCEPTION;
        }
    }

    private ApplicationRequestBlock createApplicationRequestBlock(Application application, Student student) {
        return ApplicationRequestBlock.builder()
                .application(application)
                .student(student)
                .build();
    }

    private List<ApplicationRequest> toApplicationRequestsFromRequest(
            List<ApplicationRequestRequest> requests,
            ApplicationRequestBlock block
    ) {
        return requests.stream()
                .map(r ->
                        r.getUserResponseList().stream()
                                .map(a -> r.toEntity(applicationFacade.findApplicationQuestionById(r.getId()), block, a))
                                .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
