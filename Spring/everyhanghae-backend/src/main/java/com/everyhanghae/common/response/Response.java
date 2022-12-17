package com.everyhanghae.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Response {

    private int status;
    private String msg;

    public Response(ResponseMessage responseMessage) {
        this.status = responseMessage.getStatus();
        this.msg = responseMessage.getMsg();
    }

}
