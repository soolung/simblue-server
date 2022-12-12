package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.ApplicationRequest;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestBlockRepository;
import com.soogung.simblue.domain.application.domain.repository.ApplicationRequestRepository;
import com.soogung.simblue.domain.application.facade.ApplicationFacade;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequestBlockRequest;
import com.soogung.simblue.domain.application.presentation.dto.request.ApplicationRequestRequest;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ApplicationRequestBlock block = applicationRequestBlockRepository.save(createApplicationRequestBlock(id));
        applicationRequestRepository.saveAll(
                toApplicationRequestsFromRequest(
                        request.getApplicationRequestRequests(), block));
    }

    private ApplicationRequestBlock createApplicationRequestBlock(Long id) {
        return ApplicationRequestBlock.builder()
                .application(applicationFacade.findApplicationById(id))
                .student(userFacade.findStudentByUser(userFacade.getCurrentUser()))
                .build();
    }

    private List<ApplicationRequest> toApplicationRequestsFromRequest(
            List<ApplicationRequestRequest> requests,
            ApplicationRequestBlock block
    ) {
        return requests.stream()
                .map(r ->
                        r.getAnswer().stream()
                                .map(a -> r.toEntity(applicationFacade.findApplicationQuestionById(r.getApplicationQuestionId()), block, a))
                                .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
