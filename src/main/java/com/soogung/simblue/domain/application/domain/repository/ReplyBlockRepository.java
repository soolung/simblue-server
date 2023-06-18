package com.soogung.simblue.domain.application.domain.repository;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyBlockRepository extends JpaRepository<ReplyBlock, Long>, ReplyBlockRepositoryCustom {

    boolean existsByApplicationAndUser(Application application, User student);

    boolean existsByApplication(Application application);

    @Query("SELECT b FROM ReplyBlock b " +
            "JOIN FETCH b.application " +
            "WHERE b.user = :user AND b.application.state <> 'DELETED' " +
            "ORDER BY b.id DESC")
    List<ReplyBlock> findAllByStudent(@Param("user") User user);

    int countByApplication(Application application);
}
