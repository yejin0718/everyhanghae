package com.everyhanghae.user.dto;

import com.everyhanghae.user.entity.User;
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
