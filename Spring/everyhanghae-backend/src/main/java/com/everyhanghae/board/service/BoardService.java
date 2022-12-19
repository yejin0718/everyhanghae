package com.everyhanghae.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
