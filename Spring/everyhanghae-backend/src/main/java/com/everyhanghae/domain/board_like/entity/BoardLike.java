package com.everyhanghae.domain.board_like.entity;

import com.everyhanghae.domain.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boardId;

    @Column(nullable = true)
    private Long userId;

    public BoardLike(Board boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

}
