package com.everyhanghae.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RequestCreateUser {

    @NotNull(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
            , message = "email 형식을 지켜주세요")
    private String email;

    @NotNull(message = "비밀번호 입력은 필수 입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&])[A-Za-z\\d@$!#%*?&]{8,15}$",
            message = "비밀번호는 8~15자리의 대소문자,숫자,특수문자로 이루어져야 합니다.")
    private String password;

    @NotNull(message = "닉네임 입력은 필수 입니다.")
    private String nickname;

    private int generation;
}