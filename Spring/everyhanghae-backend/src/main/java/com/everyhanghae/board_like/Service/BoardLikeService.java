package com.everyhanghae.board_like.Service;


import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.board_like.entity.BoardLike;
import com.everyhanghae.board_like.Repository.BoardLikeRepository;

import com.everyhanghae.user.entity.User;
import com.everyhanghae.user.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_BOARD_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_USER_EXCEPTION_MSG;


@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean boardLike(Long id, String email) {

        boolean likeboolean = false;

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_USER_EXCEPTION_MSG.getMsg())
        );

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_BOARD_EXCEPTION_MSG.getMsg())
        );

        Optional<BoardLike> like = boardLikeRepository.findBoardLikeByBoardIdAndUserId(board, user.getUserId());

        if(like.isPresent()){ // 이미 좋아요를 했을 때
            BoardLike boardLike = like.get();
            board.unLike();
            boardLikeRepository.delete(boardLike);
        }else{
            BoardLike boardLike = new BoardLike(board, user.getUserId());
            board.like();
            boardLikeRepository.save(boardLike);
            likeboolean = true;
        }

        return likeboolean;
    }


}
