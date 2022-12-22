package com.everyhanghae.domain.comment.service;

import com.everyhanghae.domain.board.entity.Board;
import com.everyhanghae.domain.board.repository.BoardRepository;
import com.everyhanghae.domain.comment.dto.RequestComment;
import com.everyhanghae.domain.comment.dto.ResponseComment;
import com.everyhanghae.domain.comment.entity.Comment;
import com.everyhanghae.domain.comment.mapper.CommentMapper;
import com.everyhanghae.domain.comment.repository.CommentRepository;
import com.everyhanghae.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyhanghae.shared.exception.ExceptionMessage.NOT_AUTHOR_USER_EXCEPTION_MSG;
import static com.everyhanghae.shared.exception.ExceptionMessage.NOT_MATCH_BOARD_AND_COMMENT_EXCEPTION_MSG;
import static com.everyhanghae.shared.exception.ExceptionMessage.NO_EXIST_BOARD_EXCEPTION_MSG;
import static com.everyhanghae.shared.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    /*
     * 댓글 작성
     */
    @Transactional
    public ResponseComment createComment(Long boardId, RequestComment requestDto, User user) {
        /* board 있는지 확인 */
        Board board = checkBoard(boardId);

        /* 댓글 저장 */
        Comment comment = commentMapper.toDepthZeroComment(board, requestDto, user.getUserId());
        commentRepository.save(comment);

        return new ResponseComment(boardId, comment);
    }

    /*
     * 댓글 수정
     */
    @Transactional
    public ResponseComment editComment(Long boardId, Long commentId, RequestComment requestDto, User user) {
        //댓글 확인, 수정
        Comment comment = checkComment(commentId);

        // 유저 확인
        if (comment.getUserId() != user.getUserId()) {
            throw new IllegalArgumentException(NOT_AUTHOR_USER_EXCEPTION_MSG.getMsg());
        }

        //게시글 확인
        Board board = checkBoard(boardId);
        if (comment.getBoardId().getBoardId() != board.getBoardId()) {
            throw new IllegalArgumentException(NOT_MATCH_BOARD_AND_COMMENT_EXCEPTION_MSG.getMsg());
        }

        /* 수정한 댓글 변수에 담기 */
        String editComment = requestDto.getComment();
        comment.update(editComment);

        return new ResponseComment(boardId, comment);
    }

    /*
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long boardId, Long commentId, User user) {
        //댓글 확인
        Comment comment = checkComment(commentId);

        //유저 확인
        if (comment.getUserId() != user.getUserId()) {
            throw new IllegalArgumentException(NOT_AUTHOR_USER_EXCEPTION_MSG.getMsg());
        }

        //게시글 확인
        Board board = checkBoard(boardId);
        if (comment.getBoardId().getBoardId() != board.getBoardId()) {
            throw new IllegalArgumentException(NOT_MATCH_BOARD_AND_COMMENT_EXCEPTION_MSG.getMsg());
        }

        //댓글 삭제
        commentRepository.delete(comment);
    }


    /*
     * board확인
     */
    private Board checkBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_BOARD_EXCEPTION_MSG.getMsg())
        );
    }

    /*
     * comment확인
     */
    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg())
        );
    }

}
