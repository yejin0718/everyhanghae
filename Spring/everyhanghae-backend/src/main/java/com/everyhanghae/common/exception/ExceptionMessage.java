package com.everyhanghae.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    //comment
    NO_EXIST_BOARD_EXCEPTION_MSG(404, "게시글이 존재하지 않습니다.");

    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }

}
