package com.soogung.simblue.domain.application.domain;

import com.soogung.simblue.domain.user.domain.Student;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "replyBlock", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public ReplyBlock(Student student, Application application) {
        this.student = student;
        this.application = application;
    }
}
