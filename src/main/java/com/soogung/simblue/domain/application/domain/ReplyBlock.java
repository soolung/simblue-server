package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.AuthorityMismatchException;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_reply_block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReplyBlock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "replyBlock")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public ReplyBlock(User user, Application application) {
        this.user = user;
        this.application = application;
    }

    public void validatePermission(User user) {
        if (!Objects.equals(this.user.getId(), user.getId())) {
            throw AuthorityMismatchException.EXCEPTION;
        }
    }
}
