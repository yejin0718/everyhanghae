package com.everyhanghae.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseBoardListItem {

	private Long id;
	private String title;
	private String writer;
	private String category;
	private int likeCount;
	private int commentCount;

}
