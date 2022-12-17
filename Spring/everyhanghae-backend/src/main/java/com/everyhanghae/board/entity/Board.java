package com.everyhanghae.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.everyhanghae.common.Timestamped;

import lombok.AccessLevel;
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


}
