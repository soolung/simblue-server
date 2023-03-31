package com.soogung.simblue.domain.group.domain.repository;

import com.soogung.simblue.domain.group.domain.Group;
import com.soogung.simblue.domain.group.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {

    List<Group> findAll();
}
