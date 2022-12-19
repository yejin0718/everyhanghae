package com.everyhanghae.board.controller;

import static com.everyhanghae.common.response.ResponseMessage.CREATE_BOARD_SUCCESS_MSG;
import static com.everyhanghae.common.response.ResponseMessage.GET_ALL_BOARDS_SUCCESS_MSG;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everyhanghae.board.dto.ResponseBoardListItem;
import com.everyhanghae.board.dto.RequestCreateBoard;
import com.everyhanghae.board.dto.ResponseBoard;
import com.everyhanghae.board.service.BoardService;
import com.everyhanghae.common.response.DataResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public DataResponse<ResponseBoard> writeBoard(@Valid @RequestBody RequestCreateBoard requestDto) {
		ResponseBoard response = boardService.createBoard(requestDto);
		return new DataResponse<>(CREATE_BOARD_SUCCESS_MSG, response);
	}

	@GetMapping
	public DataResponse<List<ResponseBoardListItem>> readAllBoards() {
		List<ResponseBoardListItem> response = boardService.findAllBoards();
		return new DataResponse<>(GET_ALL_BOARDS_SUCCESS_MSG, response);
	}

}
