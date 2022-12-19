package com.everyhanghae.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage implements ExceptionMessageConstant {

	// boardLike
	INTERNAL_SERVER_ERROR(500, SERVER_ERROR_MSG),

	// board
	WRONG_CATEGORY_EXCEPTION(400, WRONG_CATEGORY_ERROR_MSG),
	WRONG_BOARD_TITLE_LENGTH_EXCEPTION(400, WRONG_BOARD_LENGTH_TITLE_ERROR_MSG),
	WRONG_BOARD_WRITER_LENGTH_EXCEPTION(400, WRONG_BOARD_LENGTH_WRITER_ERROR_MSG),
	NO_EXIST_BOARD_EXCEPTION_MSG(404, "게시글이 존재하지 않습니다."),

	//comment
	NO_EXIST_COMMENT_EXCEPTION_MSG(404, "댓글이 존재하지 않습니다.");

	private final int status;
	private final String msg;

	ExceptionMessage(final int status, final String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static ExceptionMessage of(String msg) {
		ExceptionMessage exceptionMessage = null;

		switch (msg) {
			case SERVER_ERROR_MSG:
				exceptionMessage = INTERNAL_SERVER_ERROR;
				break;
			case WRONG_BOARD_LENGTH_TITLE_ERROR_MSG:
				exceptionMessage = WRONG_BOARD_TITLE_LENGTH_EXCEPTION;
				break;
			case WRONG_BOARD_LENGTH_WRITER_ERROR_MSG:
				exceptionMessage = WRONG_BOARD_WRITER_LENGTH_EXCEPTION;
				break;
			case WRONG_CATEGORY_ERROR_MSG:
				exceptionMessage = WRONG_CATEGORY_EXCEPTION;
				break;
		}

		return exceptionMessage;
	}

}
