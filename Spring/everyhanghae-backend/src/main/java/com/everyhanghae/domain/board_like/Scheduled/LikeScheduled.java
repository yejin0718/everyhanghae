package com.everyhanghae.domain.board_like.Scheduled;

import com.everyhanghae.domain.board.entity.Board;
import com.everyhanghae.domain.board.repository.BoardRepository;
import com.everyhanghae.domain.board_like.Repository.BoardLikeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeScheduled {

    private final BoardLikeRepository boardLikeRepository;

    private final BoardRepository boardRepository;

/*
아래 @Scheduled 주석을 풀면 10초에 한번 좋아요를 갱신합니다!
스케줄 동작을 확인하기 위해서는 BoardLikeService에서
board.unLike(board);과 board.like(board);를 주석처리 해야합니다!
*/

//    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void likeUpdate(){

        System.out.println("좋아요 갱신");
        List<Board> boardList;

        boardList = boardRepository.findAll();

        for (Board board : boardList){
            int count = boardLikeRepository.countByBoardId(board);
            board.likeUpdate(count);
        }

    }

}
