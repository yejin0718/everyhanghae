package com.everyhanghae.comment.controller;

import com.everyhanghae.comment.dto.CommentRequestDto;
import com.everyhanghae.comment.dto.CommentResponseDto;
import com.everyhanghae.comment.service.CommentService;
import com.everyhanghae.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.everyhanghae.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/comment")
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping
    public DataResponse<CommentResponseDto> writeComment(@PathVariable Long boardId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(boardId, requestDto);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, commentResponseDto);
    }

}