package com.soogung.simblue.domain.application.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.type.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
                .where(application.state.eq(State.ALWAYS))
                .fetch();
    }

    @Override
    public List<Application> findTheLatestApplication() {
        LocalDateTime now = LocalDateTime.now();

        return queryFactory
                .selectFrom(application)
                .where(application.state.eq(State.ALWAYS)
                        .or(application.state.eq(State.OPENED)
                                .and(application.startDate.after(now).not())
                                .and(application.endDate.before(now).not())
                        )
                )
                .orderBy(application.id.desc())
                .fetch();
    }

    @Override
    public List<Application> findApplicationClosingDeadline() {
        LocalDateTime now = LocalDateTime.now();

        return queryFactory
                .selectFrom(application)
                .where(application.state.eq(State.OPENED)
                        .and(application.startDate.after(now).not())
                        .and(application.endDate.before(now).not())
                )
                .orderBy(application.endDate.asc())
                .fetch();
    }

    @Override
    public List<Application> searchApplication(String q) {
        return queryFactory
                .selectFrom(application)
                .where(application.state.eq(State.ALWAYS)
                        .or(application.state.eq(State.OPENED))
                        .and(
                                application.title.contains(q)
                                        .or(application.description.contains(q))
                        )
                )
                .fetch();
    }
}
