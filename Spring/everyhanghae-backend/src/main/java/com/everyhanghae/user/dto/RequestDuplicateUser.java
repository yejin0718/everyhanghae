package com.everyhanghae.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class RequestDuplicateUser {

@NotNull(message = "이메일 입력은 필수 입니다.")
@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
        , message = "email 형식을 지켜주세요")
    private String email;
}
