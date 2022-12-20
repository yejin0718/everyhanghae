package com.everyhanghae.user.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtPayload {

	private final String subject;
}
