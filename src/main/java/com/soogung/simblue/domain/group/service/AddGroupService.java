package com.soogung.simblue.domain.group.service;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.Member;
import com.soogung.simblue.domain.group.domain.repository.GroupRepository;
import com.soogung.simblue.domain.group.domain.repository.MemberRepository;
import com.soogung.simblue.domain.group.facade.GroupFacade;
import com.soogung.simblue.domain.group.presentation.dto.request.AddGroupRequest;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddGroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final UserFacade userFacade;
    private final GroupFacade groupFacade;

    @Transactional
    public void execute(Long id, AddGroupRequest request) {

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
