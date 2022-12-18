package com.everyhanghae.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // boardLike
    INSERT_LIKE_SUCCESS_MSG(200,"게시글 좋아요를 성공했습니다."),
    // comment
    CREATE_COMMENT_SUCCESS_MSG(200, "댓글 작성을 성공했습니다."),
    UPDATE_COMMENT_SUCCESS_MSG(200, "댓글 수정을 성공했습니다."),
    DELETE_COMMENT_SUCCESS_MSG(200, "댓글 삭제를 성공했습니다."),
    // signup
    CREATE_USER_SUCCESS_MSG(201, "회원가입이 성공했습니다.");

    private final int status;
    private final String msg;

    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }


}
