package com.everyhanghae.board.mapper;

import com.everyhanghae.board.dto.RequestCreateBoard;
import com.everyhanghae.board.dto.ResponseBoard;
import com.everyhanghae.board.dto.ResponseBoardListItem;
import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.entity.BoardCategory;
import com.everyhanghae.comment.dto.ResponseComment;
import com.everyhanghae.comment.entity.Comment;
import com.everyhanghae.comment.mapper.CommentMapper;
import com.everyhanghae.user.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BoardMapper {

	private final CommentMapper commentMapper;

	public Board toBoard(RequestCreateBoard requestDto, User user) {
		String category = requestDto.getCategory();
		BoardCategory boardCategory = null;
		if (!category.isEmpty()) {
			boardCategory = BoardCategory.valueOf(category);
		}

		return Board.builder()
			.title(requestDto.getTitle())
			.writer(user.getNickname())
			.content(requestDto.getContent())
			.category(boardCategory)
			.likeCount(0)
			.userId(user.getUserId())
			.build();
	}

	public ResponseBoard toResponse(Board board) {
		List<Comment> commentList = board.getCommentList();
		List<ResponseComment> commentResponseDtoList = new ArrayList<>();
		if (commentList != null) {	// commentList -> CommentResponseDtoList 로 변환
			commentResponseDtoList = commentList.stream()
				.map(c -> commentMapper.toResponse(c))
				.collect(Collectors.toList());
		}

		String category = "";
		if (board.getCategory() != null) {
			category = board.getCategory().name();
		}

		return ResponseBoard.builder()
			.id(board.getBoardId())
			.title(board.getTitle())
			.writer(board.getWriter())
			.content(board.getContent())
			.category(category)
			.createdAt(board.getCreatedAt())
			.likeCount(board.getLikeCount())
			.commentList(commentResponseDtoList)
			.build();
	}

	public ResponseBoardListItem toListResponseItem(Board board) {
		String category = "";
		if (board.getCategory() != null) {
			category = board.getCategory().name();
		}

		return ResponseBoardListItem.builder()
			.id(board.getBoardId())
			.title(board.getTitle())
			.writer(board.getWriter())
			.category(category)
			.likeCount(board.getLikeCount())
			.commentCount(board.getCommentList().size())
			.build();
	}

}
