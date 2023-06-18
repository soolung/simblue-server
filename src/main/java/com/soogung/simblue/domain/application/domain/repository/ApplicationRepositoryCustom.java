package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepositoryCustom {

    Optional<Application> findApplicationById(Long id);
    List<Application> findAlwaysApplication();
    List<Application> findTheLatestApplication();
    List<Application> findApplicationClosingDeadline();
    List<Application> searchApplication(String q);
}
