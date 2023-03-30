package com.soogung.simblue.domain.group.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JoinGroupRequest {

    private List<MemberRequest> memberList;
}
