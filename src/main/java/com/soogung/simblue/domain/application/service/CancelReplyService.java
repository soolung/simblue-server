package com.soogung.simblue.domain.application.service;

import com.soogung.simblue.domain.application.domain.repository.ReplyBlockRepository;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelReplyService {

    private final ReplyBlockRepository replyBlockRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long replyBlockId) {
        replyBlockRepository.deleteByIdAndStudent(
                replyBlockId, userFacade.findStudentByUser(userFacade.getCurrentUser()));
    }
}
