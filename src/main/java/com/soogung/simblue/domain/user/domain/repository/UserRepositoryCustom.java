package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> searchUser(String q, Authority authority);
}
