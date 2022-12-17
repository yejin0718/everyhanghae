package com.everyhanghae.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage implements ExceptionMessageConstant {



	private final int status;
	private final String msg;

	ExceptionMessage(final int status, final String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static ExceptionMessage of(String msg) {
		ExceptionMessage exceptionMessage = null;



		return exceptionMessage;
	}
}
