package com.soogung.simblue.domain.user.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.repository.vo.SearchUserVO;
import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserResponse {

    private Long userId;
    private String name;
    private String studentNumber;
    private Authority authority;

    public static SearchUserResponse of(SearchUserVO vo) {
        return SearchUserResponse.builder()
                .userId(vo.getUserId())
                .name(vo.getName())
                .studentNumber(vo.getStudentNumber())
                .authority(vo.getAuthority())
                .build();
    }
}
