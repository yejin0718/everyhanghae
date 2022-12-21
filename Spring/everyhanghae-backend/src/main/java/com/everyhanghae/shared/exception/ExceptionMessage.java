package com.everyhanghae.shared.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // boardLike
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다."),

    // board
    WRONG_CATEGORY_EXCEPTION(400, "카테고리 값이 잘못 됐습니다."),
    WRONG_BOARD_TITLE_LENGTH_EXCEPTION(400, "게시글 제목의 길이가 잘못 됐습니다."),
    WRONG_BOARD_WRITER_LENGTH_EXCEPTION(400, "게시글 작성자의 길이가 잘못 됐습니다."),
    NO_EXIST_BOARD_EXCEPTION_MSG(404, "게시글이 존재하지 않습니다."),

    //comment
    NO_EXIST_COMMENT_EXCEPTION_MSG(404, "댓글이 존재하지 않습니다."),
    NOT_COMMENT_WRITER_EXCEPTION_MSG(404, "댓글 작성자가 아닙니다."),

    // user
    TOKEN_ERROR_EXCEPTION_MSG(400, "Token Error"),
    NO_EXIST_USER_EXCEPTION_MSG(404, "유저가 존재하지 않습니다."),
    NOT_AUTHOR_USER_EXCEPTION_MSG(400, "작성자가 아닙니다.");


    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }

}
