package com.everyhanghae.comment.service;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.comment.dto.RequestComment;
import com.everyhanghae.comment.dto.ResponseComment;
import com.everyhanghae.comment.entity.Comment;
import com.everyhanghae.comment.mapper.CommentMapper;
import com.everyhanghae.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_BOARD_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    //댓글 작성
    @Transactional
    public ResponseComment createComment(Long id, RequestComment requestDto) {
        //유저 확인(추가 예정)

        //board있는지 확인
        Board board = checkBoard(id);

        //댓글 저장
        Comment comment = commentMapper.toDepthZeroComment(board, requestDto);
        commentRepository.save(comment);

        return new ResponseComment(id, comment);
    }

    //댓글 수정
    @Transactional
    public ResponseComment editComment(Long boardId, Long commentId, RequestComment requestDto) {

        //수정한 댓글 변수에 담기
        String editComment = requestDto.getComment();

        //유저 확인(추가 예정)

        //게시글 확인
        checkBoard(boardId);

        //댓글 확인, 수정
        Comment comment = checkComment(commentId);
        comment.update(editComment);

        return new ResponseComment(boardId, comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        //유저 확인(추가 예정)

        //게시글 확인
        checkBoard(boardId);

        //댓글 확인
        Comment comment = checkComment(commentId);

        //유저 권한 체크(추가 예정)

        //댓글 삭제
        commentRepository.delete(comment);

    }



    //board확인
    private Board checkBoard(Long id){
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_BOARD_EXCEPTION_MSG.getMsg())
        );
    }

    //comment확인
    private Comment checkComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg())
        );
    }

}
