package com.soogung.simblue.domain.group.domain.repository;

import com.soogung.simblue.domain.group.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
