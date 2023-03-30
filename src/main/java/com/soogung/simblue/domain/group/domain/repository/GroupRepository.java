package com.soogung.simblue.domain.group.domain.repository;

import com.soogung.simblue.domain.group.domain.Group;
import org.springframework.data.repository.CrudRepository;

lsimport java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {

    List<Group> findAll();
}
