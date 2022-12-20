package com.everyhanghae.domain.user.dto;

import com.everyhanghae.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInfoUser {

    private String nickname;

    private int generation;

    public ResponseInfoUser(User user) {
        this.nickname = user.getNickname();
        this.generation = user.getGeneration();
    }
}
