package com.everyhanghae.board.entity;

import com.everyhanghae.common.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int likeCount;

    @Column
    private Long userId;

    public void unLike() {
        this.likeCount--;
    }

    public void like() {
        this.likeCount++;
    }

    public void likeUpdate(int count) {
        this.likeCount = count;
    }
}
