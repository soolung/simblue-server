package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import com.soogung.simblue.domain.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRequestBlockRepository extends JpaRepository<ApplicationRequestBlock, Long> {

    void deleteApplicationRequestBlockByApplicationAndStudent(Application application, Student student);
}
