package com.soogung.simblue.domain.banner.domain.repository;

import com.soogung.simblue.domain.banner.domain.Banner;
import com.soogung.simblue.domain.banner.domain.type.State;
import com.soogung.simblue.domain.user.domain.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends CrudRepository<Banner, Long> {

    List<Banner> findByState(State state);

    @Query("SELECT b FROM Banner b " +
            "WHERE b.teacher = :teacher AND b.state <> 'DELETED'")
    List<Banner> findByTeacher(Teacher teacher);

    @Query("SELECT b FROM Banner b " +
            "JOIN FETCH b.teacher " +
            "WHERE b.id = :id AND b.state = 'ACTIVE'")
    Optional<Banner> findBannerById(Long id);
}
