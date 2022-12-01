package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationQuestionRepository extends JpaRepository<ApplicationQuestion, Long> {

    List<ApplicationQuestion> findByApplicationIdOrderById(Long applicationId);
}
