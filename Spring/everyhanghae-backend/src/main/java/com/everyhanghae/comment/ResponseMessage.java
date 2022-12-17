package com.everyhanghae.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMessage {

    private int Status;

    private String msg;

    //Dto 데이터 추가
    private Object data;

}
