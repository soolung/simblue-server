package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.application.exception.ApplicationHasAlreadyEndedException;
import com.soogung.simblue.domain.application.exception.ApplicationHasNotStartedYetException;
import com.soogung.simblue.domain.application.exception.CanNotUpdateReplyException;
import com.soogung.simblue.domain.notice.domain.Notice;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = true, length = 200)
    private String description;

    @Column(nullable = true)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    @Column(nullable = false, length = 10)
    private String emoji;

    @Column(nullable = false)
    private Boolean isAlways;

    @Column(nullable = false)
    private Boolean allowsDuplication;

    @Column(nullable = false)
    private Boolean allowsUpdatingReply;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Notice> noticeList = new ArrayList<>();

    @Builder
    public Application(String title, String description, LocalDate startDate, LocalDate endDate, String emoji, Boolean isAlways, Boolean allowsDuplication, Boolean allowsUpdatingReply) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emoji = emoji;
        this.isAlways = isAlways;
        this.allowsDuplication = allowsDuplication;
        this.allowsUpdatingReply = allowsUpdatingReply;
    }

    public void validatePeriod() {
        if (isAlways) return;

        if (LocalDate.now().isBefore(startDate)) {
            throw ApplicationHasNotStartedYetException.EXCEPTION;
        }

        if (LocalDate.now().isAfter(endDate)) {
            throw ApplicationHasAlreadyEndedException.EXCEPTION;
        }
    }

    public void validateReplyUpdatable() {
        if (!allowsUpdatingReply) {
            throw CanNotUpdateReplyException.EXCEPTION;
        }
    }
}
