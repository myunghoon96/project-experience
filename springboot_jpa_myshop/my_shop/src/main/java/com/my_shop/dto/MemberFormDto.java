package com.my_shop.dto;

import com.my_shop.constant.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "회원 유형을 선택하세요")
    @Enumerated(EnumType.STRING)
    private Role role;
}
