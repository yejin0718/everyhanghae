package com.everyhanghae.board_like.Repository;

import com.everyhanghae.board_like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

}
