package com.everyhanghae.comment.dto;

import com.everyhanghae.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

//@Builder
@Getter
public class ResponseComment {

    private Long boardId;
    private Long commentId;

    private String username;

    private String comment;

    private LocalDateTime createdAt;

    public ResponseComment(Long id, Comment comment){
        this.boardId = id;
        this.commentId = comment.getCommentId();
        this.username = comment.getWriter();
        this.comment = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

}


