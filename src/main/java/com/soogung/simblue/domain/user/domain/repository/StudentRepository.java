package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
