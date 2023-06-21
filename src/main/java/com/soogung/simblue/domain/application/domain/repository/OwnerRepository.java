package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("SELECT o FROM Owner o " +
            "JOIN FETCH o.application " +
            "WHERE o.user = :user AND o.application.state <> 'DELETED' " +
            "ORDER BY o.id DESC")
    List<Owner> findAllByUser(@Param("user") User user);

    @Query("SELECT o FROM Owner o " +
            "JOIN FETCH o.user " +
            "WHERE o.application = :application AND o.user <> :user " +
            "ORDER BY o.id DESC")
    List<Owner> findOwnerByApplicationWithoutUser(@Param("application") Application application, @Param("user") User user);

    boolean existsByApplicationIdAndUserId(Long applicationId, Long userId);

    void deleteByApplication(Application application);
}
