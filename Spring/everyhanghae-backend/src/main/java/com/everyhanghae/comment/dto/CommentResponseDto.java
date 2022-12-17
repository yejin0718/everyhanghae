package com.everyhanghae.comment.dto;

import com.everyhanghae.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

//@Builder
@Getter
public class CommentResponseDto {

    private Long boardId;

    private String username;

    private String comment;

    private LocalDateTime createdAt;

    public CommentResponseDto(Long id, Comment comment){
        this.boardId = id;
        this.username = comment.getWriter();
        this.comment = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

}


