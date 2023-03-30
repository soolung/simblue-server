package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.repository.vo.SearchUserVO;
import com.soogung.simblue.domain.user.domain.type.Authority;

import java.util.List;

public interface UserRepositoryCustom {

    List<SearchUserVO> searchUser(String q, Authority authority);
}
