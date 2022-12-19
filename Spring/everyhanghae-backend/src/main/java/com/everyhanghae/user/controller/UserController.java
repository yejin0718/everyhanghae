package com.everyhanghae.user.controller;

import com.everyhanghae.common.response.Response;
import com.everyhanghae.user.dto.RequestCreateUser;
import com.everyhanghae.user.dto.RequestLoginUser;
import com.everyhanghae.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import static com.everyhanghae.common.response.ResponseMessage.CREATE_USER_SUCCESS_MSG;
import static com.everyhanghae.common.response.ResponseMessage.LOGIN_USER_SUCCESS_MSG;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RequestCreateUser requestCreateUser) {
        userService.signup(requestCreateUser);
        return new Response(CREATE_USER_SUCCESS_MSG);
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @ResponseBody
    @PostMapping("/login")
    public Response login(@RequestBody RequestLoginUser requestLoginUser, HttpServletResponse httpServletResponse) {
        userService.login(requestLoginUser, httpServletResponse);
        return new Response(LOGIN_USER_SUCCESS_MSG);
    }

}