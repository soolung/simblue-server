package com.soogung.simblue.domain.group.service;

import com.soogung.simblue.domain.application.presentation.dto.response.ApplicationResponse;
import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.repository.GroupRepository;
import com.soogung.simblue.domain.group.presentation.dto.response.GroupListResponse;
import com.soogung.simblue.domain.group.presentation.dto.response.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryGroupService {

    private final GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public GroupListResponse execute(){
        return new GroupListResponse(
            groupRepository.findAll()
                    .stream().map(GroupResponse::of).collect(Collectors.toList())
        );
    }

}
