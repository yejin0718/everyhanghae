package com.everyhanghae.board_like.Service;


import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.board_like.entity.BoardLike;
import com.everyhanghae.board_like.Repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;

    /*  좋아요 등록 ( 취소 미구현 ) */
    @Transactional
    public void boardLike(Long id) {

        // 유저 검증 추가 예정

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.") // ExceptionHandler 추가 예정
        );

        BoardLike boardLike = new BoardLike(board, 1L); // 임시 USER_ID 값

        // 좋아요 확인 여부 후 등록/취소 로직 추가 예정

        boardLikeRepository.save(boardLike);

    }


}
