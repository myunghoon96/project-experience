package com.springboot_jpa_mall.entity;

import com.springboot_jpa_mall.constant.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "member")
//@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column
    Long id;

    @Column(name = "member_id", unique = true)
    String memberId;

    @Column
    String password;

    @Column @Enumerated(EnumType.STRING)
    Role role;

    @Builder
    public Member (String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
        this.role = Role.ADMIN;
    }

}
