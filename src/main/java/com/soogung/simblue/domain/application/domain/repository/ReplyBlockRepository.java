package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyBlockRepository extends JpaRepository<ReplyBlock, Long>, ReplyBlockRepositoryCustom {

    boolean existsByApplicationAndStudent(Application application, Student student);

    @Query("SELECT b FROM ReplyBlock b JOIN FETCH b.application WHERE b.student = :student ORDER BY b.id DESC")
    List<ReplyBlock> findAllByStudent(Student student);

    int countByApplication(Application application);
}
