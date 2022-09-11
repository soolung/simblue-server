package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false, length = 1)
    private String emoji;

    @Column(nullable = false)
    private Boolean isAlways;

    @Builder
    public Application(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String emoji, Boolean isAlways) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emoji = emoji;
        this.isAlways = isAlways;
    }
}
