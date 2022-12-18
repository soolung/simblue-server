package com.soogung.simblue.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_student_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_student_id")
    private Long id;

    @Column(nullable = false, length = 4)
    private String studentNumber;

    @Column(nullable = false)
    private Integer admissionYear;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Builder
    public Student(String studentNumber, Integer admissionYear, User user) {
        this.studentNumber = studentNumber;
        this.admissionYear = admissionYear;
        this.user = user;
    }

    public void updateInformation(String studentNumber, Integer admissionYear) {
        this.studentNumber = studentNumber;
        this.admissionYear = admissionYear;
    }
}
