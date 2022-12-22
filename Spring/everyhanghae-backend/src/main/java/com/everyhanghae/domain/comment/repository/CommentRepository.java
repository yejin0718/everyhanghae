package com.everyhanghae.domain.comment.repository;

import com.everyhanghae.domain.comment.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Comment c WHERE c.boardId.boardId = :boardId")
	void deleteAllByIdInQuery(@Param("boardId") Long boardId);
}
