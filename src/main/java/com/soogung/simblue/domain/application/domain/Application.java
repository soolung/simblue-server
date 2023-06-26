package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.domain.repository.OwnerRepository;
import com.soogung.simblue.domain.application.domain.type.State;
import com.soogung.simblue.domain.application.domain.type.Status;
import com.soogung.simblue.domain.application.exception.ApplicationHasAlreadyEndedException;
import com.soogung.simblue.domain.application.exception.ApplicationHasNotStartedYetException;
import com.soogung.simblue.domain.application.exception.ApplicationNotFoundException;
import com.soogung.simblue.domain.application.exception.CanNotUpdateReplyException;
import com.soogung.simblue.domain.notice.domain.Notice;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Application extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = true)
    private LocalDateTime startDate;

    @Column(nullable = true)
    private LocalDateTime endDate;

    @Column(nullable = false, length = 10)
    private String emoji;

    @Column(nullable = true)
    private Integer maxReplyCount;

    @Column(nullable = false)
    private Boolean allowsDuplication;

    @Column(nullable = false)
    private Boolean allowsUpdatingReply;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private State state;

    @OneToMany(mappedBy = "application")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "notice")
    private List<Notice> noticeList = new ArrayList<>();

    @Builder
    public Application(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String emoji, Integer maxReplyCount, Boolean allowsDuplication, Boolean allowsUpdatingReply, State state) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emoji = emoji;
        this.maxReplyCount = maxReplyCount;
        this.allowsDuplication = allowsDuplication;
        this.allowsUpdatingReply = allowsUpdatingReply;
        this.state = state;
    }

    public void validateStatus() {
        if (state == State.DELETED) {
            throw ApplicationNotFoundException.EXCEPTION;
        }
    }

    public void validatePeriod() {
        if (state == State.ALWAYS) return;

        if (LocalDateTime.now().isBefore(startDate)) {
            throw ApplicationHasNotStartedYetException.EXCEPTION;
        }

        if (LocalDateTime.now().isAfter(endDate)) {
            throw ApplicationHasAlreadyEndedException.EXCEPTION;
        }
    }

    public void validateReplyUpdatable() {
        if (!allowsUpdatingReply) {
            throw CanNotUpdateReplyException.EXCEPTION;
        }
    }

    public void validatePermission(OwnerRepository ownerRepository, Long userId) {
        if (!ownerRepository.existsByApplicationIdAndUserId(id, userId)) {
            throw AuthorityMismatchException.EXCEPTION;
        }
    }

    public void delete() {
        this.state = State.DELETED;
    }

    public void close() {
        this.state = State.CLOSED;
    }

    public void updateInformation(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String emoji, Boolean allowsDuplication, Boolean allowsUpdatingReply, State state) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emoji = emoji;
        this.allowsDuplication = allowsDuplication;
        this.allowsUpdatingReply = allowsUpdatingReply;
        this.state = state;
    }

    public Status getStatus() {
        LocalDateTime now = LocalDateTime.now();

        if (state == State.ALWAYS) {
            return Status.ALWAYS;
        } else if (state == State.CLOSED || endDate.isBefore(now)) {
            return Status.DONE;
        } else if (startDate.isAfter(now)) {
            return Status.NOT_STARTED;
        }

        return Status.IN_PROGRESS;
    }

    public LocalDate getStartDate() {
        return state == State.ALWAYS ? null : startDate.toLocalDate();
    }

    public LocalDate getEndDate() {
        return state == State.ALWAYS ? null : endDate.toLocalDate();
    }
}
