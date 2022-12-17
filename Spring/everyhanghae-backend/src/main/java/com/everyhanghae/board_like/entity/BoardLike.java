package com.everyhanghae.board_like.entity;

import com.everyhanghae.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
