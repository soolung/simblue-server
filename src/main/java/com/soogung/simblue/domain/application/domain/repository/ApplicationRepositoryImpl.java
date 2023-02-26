package com.soogung.simblue.domain.application.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.type.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.soogung.simblue.domain.application.domain.QApplication.application;
import static com.soogung.simblue.domain.application.domain.QQuestion.question1;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Application> findApplicationById(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(application)
                        .innerJoin(application.questionList, question1).fetchJoin()
                        .where(application.id.eq(id))
                        .fetchOne()
        );
    }

    @Override
    public List<Application> findAlwaysApplication() {
        return queryFactory
                .selectFrom(application)
                .where(application.isAlways.eq(true)
                        .and(application.status.eq(Status.OPENED)))
                .fetch();
    }

    @Override
    public List<Application> findTheLatestApplication() {
        LocalDate now = LocalDate.now();

        return queryFactory
                .selectFrom(application)
                .where(application.status.eq(Status.OPENED)
                        .and(application.startDate.after(now).not())
                        .and(application.endDate.before(now).not())
                )
                .orderBy(application.id.desc())
                .fetch();
    }

    @Override
    public List<Application> findApplicationClosingDeadline() {
        LocalDate now = LocalDate.now();

        return queryFactory
                .selectFrom(application)
                .where(application.status.eq(Status.OPENED)
                        .and(application.isAlways.eq(false))
                        .and(application.startDate.after(now).not())
                        .and(application.endDate.before(now).not())
                )
                .orderBy(application.endDate.asc())
                .fetch();
    }
}
