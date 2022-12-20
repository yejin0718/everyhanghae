package com.everyhanghae.domain.board.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestCreateBoard {

	@NotNull(message = "게시글 제목은 필수 값 입니다.")
	@Size(min = 1, max = 30, message = "게시글 제목의 길이는 1-30 글자입니다.")
	private String title;

	@NotNull(message = "게시글 내용은 필수 값 잆니다.")
	private String content;

	@NotNull(message = "게시글 카테고리는 필수 값 잆니다.")
	@Pattern(regexp = "(^$|^BE$|^FE$|^FREE$|^SECRET$)", message = "카테고리 값이 잘못 됐습니다.")
	private String category;

}
