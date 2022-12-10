package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationNoticeRepository extends JpaRepository<ApplicationNotice, Long> {

    List<ApplicationNotice> findAllByApplicationOrderByIsPinnedDesc(Application application);
}
