package com.soogung.simblue.domain.banner.domain.repository;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.type.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BannerRepository extends CrudRepository<Banner, Long> {

    List<Banner> findByStatus(Status status);
}
