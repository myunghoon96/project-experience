package com.my_shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberFormDto {
    @NotBlank(message = "이름은 입력하세요")
    private String name;

    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Length(min = 4, max = 10)
    private String password;

    @NotBlank(message = "주소를 입력하세요")
    private String address;
}
