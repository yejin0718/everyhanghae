package com.everyhanghae.comment.mapper;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.comment.dto.RequestComment;
import com.everyhanghae.comment.dto.ResponseComment;
import com.everyhanghae.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toDepthZeroComment(Board board, RequestComment requestDto, Long userId){
        return Comment.builder()
                .content(requestDto.getComment())
                .writer(requestDto.getUsername())
                .boardId(board)
                .userId(userId)
                .build();
    }

    public ResponseComment toResponse(Comment comment) {
        return new ResponseComment(comment.getBoardId().getBoardId(), comment);
    }
}
