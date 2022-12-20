package com.everyhanghae.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // boardLike
    DELETTE_LIKE_SUCCESS_MSG(200,"게시글 좋아요를 취소했습니다."),
    INSERT_LIKE_SUCCESS_MSG(200,"게시글 좋아요를 성공했습니다."),
    // comment
    CREATE_COMMENT_SUCCESS_MSG(200, "댓글 작성을 성공했습니다."),
    UPDATE_COMMENT_SUCCESS_MSG(200, "댓글 수정을 성공했습니다."),
    DELETE_COMMENT_SUCCESS_MSG(200, "댓글 삭제를 성공했습니다."),

    // board
	GET_ALL_BOARDS_SUCCESS_MSG(200, "전체 게시글 조회를 성공했습니다."),
	GET_BOARD_SUCCESS_MSG(200, "게시글 조회를 성공했습니다."),
    CREATE_BOARD_SUCCESS_MSG(201, "게시글 작성을 성공했습니다."),
    UPDATE_BOARD_SUCCESS_MSG(200, "게시글 수정을 성공했습니다."),
    DELETE_BOARD_SUCCESS_MSG(200, "게시글 삭제를 성공했습니다."),

    // signup
    CREATE_USER_SUCCESS_MSG(201, "회원가입이 성공했습니다."),
    // duplicate
    CHECK_USER_EMAIL_SUCCESS_MSG(200, "사용 할 수 있는 이메일 입니다."),
    // login
    LOGIN_USER_SUCCESS_MSG(200, "로그인이 성공했습니다.");

    private final int status;
    private final String msg;

    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }

}
