package com.everyhanghae.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestCreateBoard {

	private String title;
	private String writer;
	private String content;
	private String category;

}
