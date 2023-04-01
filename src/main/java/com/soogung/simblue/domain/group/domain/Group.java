package com.soogung.simblue.domain.group.domain;

import com.soogung.simblue.domain.group.domain.type.GroupType;
import com.soogung.simblue.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private GroupType type;

    @Builder
    public Group(String name, GroupType type) {
        this.name = name;
        this.type = type;
    }
}
