package com.everyhanghae.domain.board.controller;

import com.everyhanghae.domain.board.dto.RequestCreateBoard;
import com.everyhanghae.domain.board.dto.RequestUpdateBoard;
import com.everyhanghae.domain.board.dto.ResponseBoard;
import com.everyhanghae.domain.board.dto.ResponseBoardListItem;
import com.everyhanghae.domain.board.service.BoardService;
import com.everyhanghae.security.UserDetailsImpl;
import com.everyhanghae.shared.response.DataResponse;
import com.everyhanghae.shared.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.everyhanghae.shared.response.ResponseMessage.CREATE_BOARD_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.DELETE_BOARD_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.GET_ALL_BOARDS_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.GET_BOARD_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.UPDATE_BOARD_SUCCESS_MSG;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public DataResponse<ResponseBoard> writeBoard(@Valid @RequestBody RequestCreateBoard requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		String email = userDetails.getUsername();
		ResponseBoard response = boardService.createBoard(requestDto, email);
		return new DataResponse<>(CREATE_BOARD_SUCCESS_MSG, response);
	}

	@GetMapping
	public DataResponse<List<ResponseBoardListItem>> readAllBoards() {
		List<ResponseBoardListItem> response = boardService.findAllBoards();
		return new DataResponse<>(GET_ALL_BOARDS_SUCCESS_MSG, response);
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<DataResponse<ResponseBoard>> readBoard(@PathVariable Long boardId) {
		ResponseBoard responseData = boardService.findBoard(boardId);
		DataResponse<ResponseBoard> response = new DataResponse<>(GET_BOARD_SUCCESS_MSG, responseData);
		return new ResponseEntity<>(response, OK);
	}

	@PatchMapping("/{boardId}")
	public ResponseEntity<DataResponse<ResponseBoard>> editBoard(@PathVariable Long boardId, @Valid @RequestBody RequestUpdateBoard requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		String email = userDetails.getUsername();
		ResponseBoard responseData = boardService.updateBoard(boardId, requestDto, email);
		DataResponse<ResponseBoard> response = new DataResponse<>(UPDATE_BOARD_SUCCESS_MSG, responseData);
		return new ResponseEntity<>(response, OK);
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Response> removeBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		String email = userDetails.getUsername();
		boardService.deleteBoard(boardId, email);
		Response response = new Response(DELETE_BOARD_SUCCESS_MSG);
		return new ResponseEntity<>(response, OK);
	}
}
