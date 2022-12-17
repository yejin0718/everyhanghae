package com.everyhanghae.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.everyhanghae.comment.entity.Comment;
import com.everyhanghae.common.Timestamped;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BoardCategory category;

	@Column(nullable = false, columnDefinition = "int default 0")
	private int likeCount;

	@Column
	private Long userId;

	@OneToMany
	private List<Comment> commentList = new ArrayList<>();

	@Builder
	public Board(String title, String writer, String content, BoardCategory category,
		int likeCount, Long userId) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.category = category;
		this.likeCount = likeCount;
		this.userId = userId;
	}

}
