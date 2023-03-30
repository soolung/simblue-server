package com.soogung.simblue.domain.group.facade;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.exception.ApplicationNotFoundException;
import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.repository.GroupRepository;
import com.soogung.simblue.domain.group.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GroupFacade {

    private final GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public Group findGroup(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> GroupNotFoundException.EXCEPTION);
    }
}
