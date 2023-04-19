package com.soogung.simblue.domain.user.domain;

import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Authority authority;

    @Builder
    public User(String email, String name, Authority authority) {
        this.email = email;
        this.name = name;
        this.authority = authority;
    }

    public void updateInformation(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User updateInformationByBsm(BsmResourceResponse resource) {
        this.name = name;
        this.password = password;

        return this;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
