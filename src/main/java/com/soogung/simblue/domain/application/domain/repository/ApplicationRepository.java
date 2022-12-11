package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByIsAlways(Boolean isAlways);

    List<Application> findAllByOrderByIdDesc();

    List<Application> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT a FROM Application a " +
            "WHERE a.endDate >= current_date AND a.startDate <= current_date AND a.isAlways = false " +
            "ORDER BY a.endDate ASC")
    List<Application> findAllClosingDeadline();
}
