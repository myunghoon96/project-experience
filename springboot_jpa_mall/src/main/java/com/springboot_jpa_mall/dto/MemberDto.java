package com.springboot_jpa_mall.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "memberId NotBlank")
    String memberLoginId;

    @NotBlank(message = "password NotBlank")
    @Length(min = 4, max = 8, message = "password 길이 4~8")
    String password;

    public MemberDto (String memberLoginId, String password) {
        this.memberLoginId = memberLoginId;
        this.password = password;
    }
}
