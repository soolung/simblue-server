package com.soogung.simblue.domain.group.service;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.Member;
import com.soogung.simblue.domain.group.domain.repository.GroupRepository;
import com.soogung.simblue.domain.group.domain.repository.MemberRepository;
import com.soogung.simblue.domain.group.exception.GroupNotFoundException;
import com.soogung.simblue.domain.group.facade.GroupFacade;
import com.soogung.simblue.domain.group.presentation.dto.request.JoinGroupRequest;
import com.soogung.simblue.domain.group.presentation.dto.request.MemberRequest;
import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JoinGroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final UserFacade userFacade;
    private final GroupFacade groupFacade;


    public void execute(Long id,JoinGroupRequest request) {

        Group group = groupFacade.findGroup(id);

        memberRepository.saveAll(
                request.getMemberList().stream().map(
                        (member) -> Member.builder()
                                .student(userFacade.findStudentById(member.getStudentId()))
                                .group(group)
                                .build()
                ).collect(Collectors.toList())
        );

    }

}
