package com.everyhanghae.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String email;
    private String nickname;

    public KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}