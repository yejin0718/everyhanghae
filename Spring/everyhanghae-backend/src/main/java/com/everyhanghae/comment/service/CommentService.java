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

        return commentMapper.toResponseComment(id, comment);
    }

    //board확인
    private Board checkBoard(Long id){
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")//수정 해야됨
        );
    }


}
