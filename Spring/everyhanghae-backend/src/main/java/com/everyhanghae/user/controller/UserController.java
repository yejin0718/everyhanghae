package com.everyhanghae.user.controller;

import com.everyhanghae.common.response.DataResponse;
import com.everyhanghae.common.response.Response;
import com.everyhanghae.user.dto.RequestCreateUser;
import com.everyhanghae.user.dto.RequestDuplicateUser;
import com.everyhanghae.user.dto.RequestLoginUser;
import com.everyhanghae.user.dto.ResponseInfoUser;
import com.everyhanghae.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.everyhanghae.common.response.ResponseMessage.CREATE_USER_SUCCESS_MSG;
import static com.everyhanghae.common.response.ResponseMessage.LOGIN_USER_SUCCESS_MSG;
import static com.everyhanghae.common.response.ResponseMessage.CHECK_USER_EMAIL_FAIL_MSG;
import static com.everyhanghae.common.response.ResponseMessage.CHECK_USER_EMAIL_SUCCESS_MSG;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RequestCreateUser requestCreateUser) {
        userService.signup(requestCreateUser);
        return new Response(CREATE_USER_SUCCESS_MSG);
    }

    @PostMapping("/duplicate")
    public Response duplicate(@RequestBody RequestDuplicateUser requestDuplicateUser) {
        boolean duplicate = userService.duplicate(requestDuplicateUser);
        if (duplicate) {
            return new Response(CHECK_USER_EMAIL_SUCCESS_MSG);
        } else {
            return new Response(CHECK_USER_EMAIL_FAIL_MSG);
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public DataResponse<ResponseInfoUser> login(@RequestBody RequestLoginUser requestLoginUser, HttpServletResponse httpServletResponse) {
        ResponseInfoUser responseInfoUser = userService.login(requestLoginUser,httpServletResponse);
        return new DataResponse<>(LOGIN_USER_SUCCESS_MSG, responseInfoUser);
    }

}