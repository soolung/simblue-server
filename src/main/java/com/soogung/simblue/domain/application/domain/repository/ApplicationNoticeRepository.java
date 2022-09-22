package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationNoticeRepository extends JpaRepository<ApplicationNotice, Long> {
}
