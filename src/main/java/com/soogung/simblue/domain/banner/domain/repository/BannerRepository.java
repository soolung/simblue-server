package com.soogung.simblue.domain.banner.domain.repository;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.type.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends CrudRepository<Banner, Long> {

    List<Banner> findByStatus(Status status);

    @Query("SELECT b FROM Banner b JOIN FETCH b.teacher WHERE b.id = :id AND b.status = 'ACTIVE'")
    Optional<Banner> findByIdAndStatus(Long id, Status status);
}
