package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByApplicationIdOrderById(Long applicationId);
}
