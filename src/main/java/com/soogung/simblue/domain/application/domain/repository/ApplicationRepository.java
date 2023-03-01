package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationRepositoryCustom {

    @Query("SELECT a FROM Application a WHERE a.status = 'ALWAYS' OR a.status = 'OPENED'")
    List<Application> findAllByOrderByIdDesc(Pageable pageable);
}
