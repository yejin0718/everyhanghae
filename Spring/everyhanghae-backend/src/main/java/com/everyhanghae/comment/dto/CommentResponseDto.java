package com.everyhanghae.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentResponseDto {

    private Long boardId;

    private String username;

    private String comment;

    private LocalDateTime createdAt;
}
