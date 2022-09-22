package com.soogung.simblue.domain.application.domain;

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
@Table(name = "application_tbl")
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

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 1)
    private String emoji;

    @Column(nullable = false)
    private Boolean isAlways;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<ApplicationQuestion> applicationQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<ApplicationNotice> applicationNotices = new ArrayList<>();

    @Builder
    public Application(String title, String description, LocalDate startDate, LocalDate endDate, String emoji, Boolean isAlways) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emoji = emoji;
        this.isAlways = isAlways;
    }
}
