package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;

import java.util.List;

public interface ApplicationRequestBlockRepositoryCustom {

    List<ApplicationRequestBlock> findApplicationResult(Long applicationId);
}
