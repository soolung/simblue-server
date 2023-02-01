package com.soogung.simblue.domain.application.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soogung.simblue.domain.application.domain.QApplicationRequest.applicationRequest;
import static com.soogung.simblue.domain.application.domain.QApplicationRequestBlock.applicationRequestBlock;
import static com.soogung.simblue.domain.user.domain.QStudent.student;
import static com.soogung.simblue.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ApplicationRequestBlockRepositoryImpl implements ApplicationRequestBlockRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationRequestBlock> findApplicationResult(Long applicationId) {
        return queryFactory
                .selectFrom(applicationRequestBlock)
                .join(applicationRequestBlock.requests, applicationRequest).fetchJoin()
                .join(applicationRequestBlock.student, student).fetchJoin()
                .join(student.user, user).fetchJoin()
                .where(applicationRequestBlock.application.id.eq(applicationId))
                .orderBy(
                        student.studentNumber.asc(),
                        applicationRequest.id.asc()
                )
                .distinct()
                .fetch();
    }
}
