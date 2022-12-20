package com.everyhanghae.user.service;

import com.everyhanghae.user.dto.RequestCreateUser;
import com.everyhanghae.user.dto.RequestDuplicateUser;
import com.everyhanghae.user.dto.RequestLoginUser;
import com.everyhanghae.user.dto.ResponseInfoUser;
import com.everyhanghae.user.entity.User;
import com.everyhanghae.user.jwt.JwtUtil;
import com.everyhanghae.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(RequestCreateUser requestCreateUser) {
        String email = requestCreateUser.getEmail();
        String password = requestCreateUser.getPassword();
        String nickname = requestCreateUser.getNickname();
        int generation = requestCreateUser.getGeneration();


        validateDuplicateUser(requestCreateUser);  // 이메일 중복 확인
        User user = new User(email, password, nickname, generation);
        userRepository.save(user);
    }

    private void validateDuplicateUser(RequestCreateUser requestCreateUser) {
        userRepository.findByEmail(requestCreateUser.getEmail())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
                });
    }

    @Transactional(readOnly = true)
    public ResponseInfoUser login(RequestLoginUser requestLoginUser, HttpServletResponse httpServletResponse) {
        String email = requestLoginUser.getEmail();
        String password = requestLoginUser.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 이메일이 없습니다.")
        );

        ResponseInfoUser responseInfoUser = new ResponseInfoUser(user);

        // 비밀번호 확인
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 로그인 시 httpServletResponse 헤더 토큰추가
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail()));

        return responseInfoUser;
    }

    public boolean duplicate(RequestDuplicateUser requestDuplicateUser) {
        boolean emailDuplicate = true;
        Optional<User> user = userRepository.findByEmail(requestDuplicateUser.getEmail());
        if(user.isPresent()){
            emailDuplicate = false;
        }
        return emailDuplicate;
    }
}