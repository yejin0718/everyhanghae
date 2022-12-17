package com.everyhanghae.comment.service;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.comment.dto.CommentRequestDto;
import com.everyhanghae.comment.dto.CommentResponseDto;
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
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        //유저 확인(추가 예정)

        //board있는지 확인
        Board board = checkBoard(id);

        //댓글 저장
        Comment comment = commentMapper.toDepthZeroComment(board, requestDto);
        commentRepository.save(comment);

        return new CommentResponseDto(id, comment);
    }

    public CommentResponseDto editComment(Long boardId, Long commentId, CommentRequestDto requestDto) {

        //수정한 댓글 변수에 담기
        String editComment = requestDto.getComment();

        //유저 확인(추가 예정)

        //게시글 확인
        checkBoard(boardId);

        //댓글 확인, 수정
        Comment comment = checkComment(commentId);
        comment.update(editComment);

        return new CommentResponseDto(boardId, comment);
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
