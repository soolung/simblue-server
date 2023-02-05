package com.soogung.simblue.domain.user.domain.repository;

import com.soogung.simblue.domain.user.domain.Teacher;
import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUser(User user);

    @Query("SELECT t FROM Teacher t JOIN FETCH t.user WHERE t.user.authority = 'ROLE_TEACHER' AND t.user.name LIKE %:name%")
    List<Teacher> searchTeacherByName(@Param("name") String name);
}
