package com.everyhanghae.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginUser {

    private String email;

    private String password;
}
