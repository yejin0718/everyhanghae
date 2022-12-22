package com.everyhanghae.shared.response;

import lombok.Getter;

@Getter
public class DataResponse<T> extends Response{

    private T data;

    public DataResponse(ResponseMessage responseMessage, T data) {
        super(responseMessage);
        this.data = data;
    }

    public DataResponse(ResponseMessage responseMessage){
        super(responseMessage);
    }


}
