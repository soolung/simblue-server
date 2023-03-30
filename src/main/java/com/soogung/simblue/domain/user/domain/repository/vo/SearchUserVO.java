package com.soogung.simblue.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.Getter;

@Getter
public class SearchUserVO {

    private final Long userId;
    private final String name;
    private final String studentNumber;
    private final Authority authority;

    @QueryProjection
    public SearchUserVO(Long userId, String name, String studentNumber, Authority authority) {
        this.userId = userId;
        this.name = name;
        this.studentNumber = studentNumber;
        this.authority = authority;
    }
}