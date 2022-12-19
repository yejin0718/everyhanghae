package com.everyhanghae.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everyhanghae.board.dto.ResponseBoardListItem;
import com.everyhanghae.board.dto.RequestCreateBoard;
import com.everyhanghae.board.dto.ResponseBoard;
import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.mapper.BoardMapper;
import com.everyhanghae.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardMapper boardMapper;

	@Transactional
	public ResponseBoard createBoard(RequestCreateBoard requestDto) {
		// 유저 정보 가져오는 로직 추가 필요
		Long userId = 1L;

		Board board = boardMapper.toBoard(requestDto, userId);
		Board savedBoard = boardRepository.save(board);

		return boardMapper.toResponse(savedBoard);
	}

	@Transactional(readOnly = true)
	public List<ResponseBoardListItem> findAllBoards() {
		List<Board> boardList = boardRepository.findAll();
		List<ResponseBoardListItem> listResponseBoardItemList = boardList.stream()
			.map(e -> boardMapper.toListResponseItem(e))
			.collect(Collectors.toList());

		return listResponseBoardItemList;
	}

}
