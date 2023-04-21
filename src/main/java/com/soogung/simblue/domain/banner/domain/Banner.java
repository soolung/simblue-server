package com.soogung.simblue.domain.banner.domain;

import com.soogung.simblue.domain.banner.domain.type.State;
import com.soogung.simblue.domain.banner.domain.type.Status;
import com.soogung.simblue.domain.user.domain.User;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_banner")
@Entity
public class Banner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(nullable = false)
    private String imageUri;

    @Column(nullable = true)
    private String linkTo;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Banner(String imageUri, String linkTo, LocalDate endDate, User user) {
        this.imageUri = imageUri;
        this.linkTo = linkTo;
        this.endDate = endDate;
        this.state = State.ACTIVE;
        this.user = user;
    }

    public Status getStatus() {
        if (LocalDate.now().isAfter(endDate)) {
            return Status.DONE;
        }

        return Status.STARTED;
    }

    public void validatePermission(User user) {
        if (!Objects.equals(this.user.getId(), user.getId())) {
            throw AuthorityMismatchException.EXCEPTION;
        }
    }

    public void update(String imageUri, String linkTo, LocalDate endDate) {
        this.imageUri = imageUri;
        this.linkTo = linkTo;
        this.endDate = endDate;
    }

    public void delete() {
        this.state = State.DELETED;
    }
}