package com.everyhanghae.domain.board.repository;

import com.everyhanghae.domain.board.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Board b WHERE b.boardId = :boardId")
	void deleteAllByIdInQuery(@Param("boardId") Long boardId);

}
