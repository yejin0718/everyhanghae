package com.everyhanghae.domain.user.controller;


import com.everyhanghae.domain.user.service.KakaoService;
import com.everyhanghae.shared.response.DataResponse;
import com.everyhanghae.shared.response.Response;
import com.everyhanghae.domain.user.dto.RequestCreateUser;
import com.everyhanghae.domain.user.service.UserService;
import com.everyhanghae.domain.user.dto.RequestDuplicateUser;
import com.everyhanghae.domain.user.dto.RequestLoginUser;
import com.everyhanghae.domain.user.dto.ResponseInfoUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.everyhanghae.shared.response.ResponseMessage.CREATE_USER_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.LOGIN_USER_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.CHECK_USER_EMAIL_SUCCESS_MSG;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RequestCreateUser requestCreateUser) {
        userService.signup(requestCreateUser);
        return new Response(CREATE_USER_SUCCESS_MSG);
    }

    @PostMapping("/duplicate")
    public Response duplicate(@RequestBody @Valid RequestDuplicateUser requestDuplicateUser) {
        userService.duplicate(requestDuplicateUser);
        return new Response(CHECK_USER_EMAIL_SUCCESS_MSG);
    }

    @ResponseBody
    @PostMapping("/login")
    public DataResponse<ResponseInfoUser> login(@RequestBody RequestLoginUser requestLoginUser, HttpServletResponse httpServletResponse) {
        ResponseInfoUser responseInfoUser = userService.login(requestLoginUser, httpServletResponse);
        return new DataResponse<>(LOGIN_USER_SUCCESS_MSG, responseInfoUser);
    }

    @ResponseBody
    @GetMapping("/kakao/callback")
    public ResponseEntity<ResponseInfoUser> kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        System.out.println(code);
        var response = kakaoService.kakaoLogin(code);
        return response;
    }
}