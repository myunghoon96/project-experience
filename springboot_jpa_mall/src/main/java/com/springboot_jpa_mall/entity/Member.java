package com.springboot_jpa_mall.entity;

import com.springboot_jpa_mall.constant.Role;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter @Setter @ToString
@Table(name = "member")
//@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id", unique = true)
    Long id;

    @Column(name = "member_login_id", unique = true)
    String memberLoginId;

    @Column
    String password;

    @Column @Enumerated(EnumType.STRING)
    Role role;

    @Builder
    public Member (String memberLoginId, String password) {
        this.memberLoginId = memberLoginId;
        this.password = password;
        this.role = Role.ADMIN;
    }

}
