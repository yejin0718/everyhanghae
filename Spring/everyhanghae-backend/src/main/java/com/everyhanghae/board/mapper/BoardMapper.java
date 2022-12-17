package com.everyhanghae.board.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.everyhanghae.board.dto.RequestCreateBoard;
import com.everyhanghae.board.dto.ResponseBoard;
import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.entity.BoardCategory;
import com.everyhanghae.comment.dto.CommentResponseDto;
import com.everyhanghae.comment.entity.Comment;
import com.everyhanghae.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BoardMapper {

	private final CommentMapper commentMapper;

	public Board toBoard(RequestCreateBoard requestDto, Long userId) {
		String category = requestDto.getCategory();
		BoardCategory boardCategory;
		try {
			boardCategory = BoardCategory.valueOf(category);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("카테고리 값이 잘못 됐습니다.");
		}

		return Board.builder()
			.title(requestDto.getTitle())
			.writer(requestDto.getWriter())
			.content(requestDto.getContent())
			.category(boardCategory)
			.likeCount(0)
			.userId(userId)
			.build();
	}

	public ResponseBoard toResponse(Board board) {
		List<Comment> commentList = board.getCommentList();
		List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
		if (commentList != null) {	// commentList -> CommentResponseDtoList 로 변환
			commentResponseDtoList = commentList.stream()
				.map(c -> commentMapper.toResponseComment(board.getBoardId(), c))
				.collect(Collectors.toList());
		}

		return ResponseBoard.builder()
			.id(board.getBoardId())
			.title(board.getTitle())
			.writer(board.getWriter())
			.content(board.getContent())
			.category(board.getCategory().name())
			.createdAt(board.getCreatedAt())
			.likeCount(board.getLikeCount())
			.commentList(commentResponseDtoList)
			.build();
	}
}
