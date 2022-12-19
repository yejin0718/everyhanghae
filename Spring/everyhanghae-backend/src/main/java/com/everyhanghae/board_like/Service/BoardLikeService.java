package com.everyhanghae.board_like.Service;


import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.board_like.entity.BoardLike;
import com.everyhanghae.board_like.Repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;

    /*  좋아요 등록 ( 취소 미구현 ) */
    @Transactional
    public boolean boardLike(Long id) {

        boolean likeboolean = false;

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.") // ExceptionHandler 추가 예정
        );
        Optional<BoardLike> like = boardLikeRepository.findBoardLikeByBoardIdAndUserId(board, 1L); // 임시 USER_ID 값

        if(like.isPresent()){ // 이미 좋아요를 했을 때
            BoardLike boardLike = like.get();
            board.unLike();
            boardLikeRepository. delete(boardLike);
        }else{
            BoardLike boardLike = new BoardLike(board, 1L); // 임시 USER_ID 값
            board.like();
            boardLikeRepository.save(boardLike);
            likeboolean = true;
        }

        return likeboolean;
    }


}
