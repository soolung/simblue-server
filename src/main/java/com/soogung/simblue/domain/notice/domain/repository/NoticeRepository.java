package com.soogung.simblue.domain.notice.domain.repository;

import com.soogung.simblue.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByApplicationIdOrderByIsPinnedDesc(Long applicationId);
}
