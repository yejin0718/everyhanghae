package com.everyhanghae.domain.comment.controller;

import com.everyhanghae.domain.comment.dto.RequestComment;
import com.everyhanghae.domain.comment.dto.ResponseComment;
import com.everyhanghae.domain.comment.service.CommentService;
import com.everyhanghae.shared.response.DataResponse;
import com.everyhanghae.shared.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.everyhanghae.shared.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.DELETE_COMMENT_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/comments")
public class CommentController {
    private final CommentService commentService;

    /*
     * 댓글 작성
     */
    @PostMapping
    public DataResponse<ResponseComment> writeComment(@PathVariable Long boardId, @RequestBody RequestComment requestDto, HttpServletRequest request) {
        ResponseComment responseComment = commentService.createComment(boardId, requestDto, request);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    /*
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public DataResponse<ResponseComment> editComment(@PathVariable Long boardId, @PathVariable Long commentId, @RequestBody RequestComment requestDto, HttpServletRequest request){
        ResponseComment responseComment = commentService.editComment(boardId, commentId, requestDto, request);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    /*
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public Response deleteComment(@PathVariable Long boardId, @PathVariable Long commentId){
        commentService.deleteComment(boardId, commentId);
        return new Response(DELETE_COMMENT_SUCCESS_MSG);
    }


}