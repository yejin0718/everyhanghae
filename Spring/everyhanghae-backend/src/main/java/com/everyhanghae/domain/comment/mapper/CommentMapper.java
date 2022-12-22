package com.everyhanghae.domain.comment.mapper;

import com.everyhanghae.domain.board.entity.Board;
import com.everyhanghae.domain.comment.dto.RequestComment;
import com.everyhanghae.domain.comment.dto.ResponseComment;
import com.everyhanghae.domain.comment.entity.Comment;
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
