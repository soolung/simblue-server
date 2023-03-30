package com.soogung.simblue.domain.user.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.user.domain.repository.vo.QSearchUserVO;
import com.soogung.simblue.domain.user.domain.repository.vo.SearchUserVO;
import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.soogung.simblue.domain.user.domain.QStudent.*;
import static com.soogung.simblue.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SearchUserVO> searchUser(String q, Authority authority) {
        return queryFactory.select(new QSearchUserVO(
                        user.id,
                        user.name,
                        student.studentNumber,
                        user.authority
                ))
                .from(user)
                .leftJoin(student).on(
                        user.authority.eq(Authority.ROLE_STUDENT),
                        user.eq(student.user)
                ).fetchJoin()
                .where(
                        user.name.contains(q),
                        eqAuthority(authority)
                )
                .fetch();
    }

    private BooleanExpression eqAuthority(Authority authority) {
        if (Objects.isNull(authority)) {
            return null;
        }

        return user.authority.eq(authority);
    }
}
