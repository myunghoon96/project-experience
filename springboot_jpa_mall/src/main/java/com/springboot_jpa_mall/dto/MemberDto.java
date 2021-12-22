package com.springboot_jpa_mall.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {

    @NotBlank(message = "memberId NotBlank")
    String memberId;

    @NotBlank(message = "password NotBlank")
    @Length(min = 4, max = 8, message = "password 길이 4~8")
    String password;

    public MemberDto (String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
