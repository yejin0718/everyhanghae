package com.everyhanghae.board.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestCreateBoard {

	private static final String WRONG_BOARD_LENGTH_TITLE_ERROR_MSG = "게시글 제목의 길이가 잘못 됐습니다.";
	private static final String WRONG_BOARD_LENGTH_WRITER_ERROR_MSG = "게시글 작성자의 길이가 잘못 됐습니다.";
	private static final String WRONG_CATEGORY_ERROR_MSG = "카테고리 값이 잘못 됐습니다.";

	@Size(min = 1, max = 50, message = WRONG_BOARD_LENGTH_TITLE_ERROR_MSG)
	private String title;

	@Size(min = 1, max = 20, message = WRONG_BOARD_LENGTH_WRITER_ERROR_MSG)
	private String writer;

	private String content;

	@Pattern(regexp = "(^$|^BE$|^FE$|^FREE$|^SECRET$)", message = WRONG_CATEGORY_ERROR_MSG)
	private String category;

}
