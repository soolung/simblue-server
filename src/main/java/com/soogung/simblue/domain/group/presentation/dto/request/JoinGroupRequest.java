package com.soogung.simblue.domain.group.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JoinGroupRequest {

    private List<MemberRequest> memberList;

    /*
    1. 학생을 추가할 그룹을 찾는다
    2. 학생을 추가한다 ((member에 잇는)학번이랑 이름을 받는 request dto사용)

    1. 학생을 삭제할 그룹을 찾는다
    2. 학생을 삭제한다 ((member에 잇는)학번이랑 이름을 받는 request dto사용)
     */
}
