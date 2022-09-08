package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUser(User user);
}
