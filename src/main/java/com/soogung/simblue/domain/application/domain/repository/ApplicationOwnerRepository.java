package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationOwner;
import com.soogung.simblue.domain.user.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationOwnerRepository extends JpaRepository<ApplicationOwner, Long> {

    @Query("SELECT o FROM ApplicationOwner o JOIN FETCH o.application WHERE o.teacher = :teacher ORDER BY o.id DESC")
    List<ApplicationOwner> findAllByTeacher(Teacher teacher);

    boolean existsByApplicationIdAndTeacherId(Long application, Long teacher);
}
