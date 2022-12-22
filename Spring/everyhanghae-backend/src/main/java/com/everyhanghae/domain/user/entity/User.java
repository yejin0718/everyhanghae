package com.everyhanghae.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int generation;

    private Long kakaoId;

    public User(String email, String password, String nickname, int generation) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.generation = generation;
    }

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}