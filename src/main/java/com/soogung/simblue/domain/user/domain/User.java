package com.soogung.simblue.domain.user.domain;

import com.soogung.simblue.domain.user.domain.type.Authority;
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
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Authority authority;

    @Column(nullable = true, length = 4)
    private String studentNumber;

    @Column(nullable = true)
    private Integer admissionYear;

    @Builder
    public User(String name, String email, Authority authority) {
        this.name = name;
        this.email = email;
        this.authority = authority;
    }

    public void updateStudentInformation(String studentNumber, Integer admissionYear) {
        this.studentNumber = studentNumber;
        this.admissionYear = admissionYear;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return this.authority.equals(Authority.ROLE_TEACHER);
    }

    public boolean isStudent() {
        return this.authority.equals(Authority.ROLE_STUDENT);
    }
}
