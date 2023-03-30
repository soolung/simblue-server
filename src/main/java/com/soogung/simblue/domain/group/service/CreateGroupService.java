package com.soogung.simblue.domain.group.service;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.repository.GroupRepository;
import com.soogung.simblue.domain.group.presentation.dto.request.GroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateGroupService {

    final GroupRepository groupRepository;

    @Transactional
    public void execute(GroupRequest request) {
        Group group = groupRepository.save(request.toEntity());
    }

}
