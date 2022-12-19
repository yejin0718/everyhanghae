package com.everyhanghae.board_like.Repository;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board_like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    int countByBoardId(Board boardId);

    Optional<BoardLike> findBoardLikeByBoardIdAndUserId(Board boardId, Long userId);
}
