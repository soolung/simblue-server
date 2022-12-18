package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUser(User user);
}
