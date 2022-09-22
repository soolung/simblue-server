package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
}
