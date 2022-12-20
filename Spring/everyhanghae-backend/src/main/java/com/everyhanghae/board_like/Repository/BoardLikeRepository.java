package com.everyhanghae.board_like.Repository;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board_like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    int countByBoardId(Board boardId);

    Optional<BoardLike> findBoardLikeByBoardIdAndUserId(Board boardId, Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM BoardLike bl WHERE bl.boardId.boardId = :boardId")
    void deleteAllByIdInQuery(@Param("boardId") Long boardId);
}
