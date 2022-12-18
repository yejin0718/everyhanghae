package com.everyhanghae.user.service;

import com.everyhanghae.user.dto.RequestCreateUser;
import com.everyhanghae.user.entity.User;
import com.everyhanghae.user.entity.UserRole;
import com.everyhanghae.user.jwt.JwtUtil;
import com.everyhanghae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signup(RequestCreateUser requestCreateUser) {
        String email = requestCreateUser.getEmail();
        String password = requestCreateUser.getPassword();
        String nickname = requestCreateUser.getNickname();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }
        // 사용자 ROLE 확인
        UserRole userRole = UserRole.USER;
        if (requestCreateUser.isAdmin()) {
            if (!requestCreateUser.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            userRole = UserRole.ADMIN;
        }

        User user = new User(email, password, nickname, userRole);
        userRepository.save(user);
    }
}
