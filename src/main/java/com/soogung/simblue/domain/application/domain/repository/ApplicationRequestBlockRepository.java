package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import com.soogung.simblue.domain.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRequestBlockRepository extends JpaRepository<ApplicationRequestBlock, Long> {

    void deleteApplicationRequestBlockByApplicationAndStudent(Application application, Student student);

    boolean existsByApplicationAndStudent(Application application, Student student);

    @Query("SELECT b FROM ApplicationRequestBlock b JOIN FETCH b.application WHERE b.student = :student ORDER BY b.id DESC")
    List<ApplicationRequestBlock> findAllByStudent(Student student);

    @Query("SELECT DISTINCT b FROM ApplicationRequestBlock b JOIN FETCH b.requests WHERE b.application.id = :applicationId")
    List<ApplicationRequestBlock> findApplicationResult(Long applicationId);
}
