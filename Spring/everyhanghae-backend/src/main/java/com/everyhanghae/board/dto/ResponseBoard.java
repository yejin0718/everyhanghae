package com.everyhanghae.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.everyhanghae.comment.dto.CommentResponseDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseBoard {

	private Long id;
	private String title;
	private String writer;
	private String content;
	private String category;
	private LocalDateTime createdAt;
	private int likeCount;
	private List<CommentResponseDto> commentList;

}
