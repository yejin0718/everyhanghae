package com.everyhanghae.domain.board.dto;

import com.everyhanghae.domain.comment.dto.ResponseComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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
	private List<ResponseComment> commentList;

}
