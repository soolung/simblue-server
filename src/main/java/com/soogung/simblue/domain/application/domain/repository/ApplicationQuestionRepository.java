package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationQuestionRepository extends JpaRepository<ApplicationQuestion, Long> {
}
