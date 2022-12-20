package com.everyhanghae.user.jwt;

import com.everyhanghae.user.entity.UserRole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtPayload {

	private final String subject;
	private final UserRole userRole;

}
