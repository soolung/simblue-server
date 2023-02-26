package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<Application> findAlwaysApplication();
    List<Application> findTheLatestApplication();
    List<Application> findApplicationClosingDeadline();
}
