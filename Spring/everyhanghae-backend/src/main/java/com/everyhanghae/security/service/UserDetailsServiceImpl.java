package com.everyhanghae.security.service;

import com.everyhanghae.domain.user.entity.User;
import com.everyhanghae.domain.user.repository.UserRepository;
import com.everyhanghae.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.everyhanghae.shared.exception.ExceptionMessage.NO_EXIST_USER_EXCEPTION_MSG;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(NO_EXIST_USER_EXCEPTION_MSG.getMsg()));

        return new UserDetailsImpl(user, user.getEmail());
    }
}