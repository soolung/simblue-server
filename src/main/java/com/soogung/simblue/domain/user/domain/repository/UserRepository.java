package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
