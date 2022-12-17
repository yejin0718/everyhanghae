package com.everyhanghae.comment.mapper;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.comment.dto.CommentRequestDto;
import com.everyhanghae.comment.dto.CommentResponseDto;
import com.everyhanghae.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDto toResponseComment(Long id, Comment comment) {
        return CommentResponseDto.builder()
                .boardId(id)
                .username(comment.getWriter())
                .comment(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public Comment toDepthZeroComment(Board board, CommentRequestDto requestDto){
        return Comment.builder()
                .content(requestDto.getComment())
                .writer(requestDto.getUsername())
                .boardId(board)
                .build();
    }

}
