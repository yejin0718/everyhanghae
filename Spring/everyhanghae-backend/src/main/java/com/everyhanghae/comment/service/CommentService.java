package com.everyhanghae.comment.service;

import com.everyhanghae.board.entity.Board;
import com.everyhanghae.board.repository.BoardRepository;
import com.everyhanghae.comment.dto.RequestComment;
import com.everyhanghae.comment.dto.ResponseComment;
import com.everyhanghae.comment.entity.Comment;
import com.everyhanghae.comment.mapper.CommentMapper;
import com.everyhanghae.comment.repository.CommentRepository;
import com.everyhanghae.user.entity.User;
import com.everyhanghae.user.jwt.JwtUtil;
import com.everyhanghae.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_BOARD_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_COMMENT_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.NO_EXIST_USER_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.TOKEN_ERROR_EXCEPTION_MSG;
import static com.everyhanghae.common.exception.ExceptionMessage.WRONG_COMMENT_WRITER_EXCEPTION;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final JwtUtil jwtUtil;
    Claims claims = null;

    /*
     * 댓글 작성
     */
    @Transactional
    public ResponseComment createComment(Long id, RequestComment requestDto, HttpServletRequest request) {

        /* 로그인 확인 */
        String token = jwtUtil.resolveToken(request);
        claims = checkToken(token);
        User user = findUser();

        /* board 있는지 확인 */
        Board board = checkBoard(id);

        /* 댓글 저장 */
        Comment comment = commentMapper.toDepthZeroComment(board, requestDto, user.getUserId());
        commentRepository.save(comment);

        return new ResponseComment(id, comment);
    }

    /*
     * 댓글 수정
     */
    @Transactional
    public ResponseComment editComment(Long boardId, Long commentId, RequestComment requestDto, HttpServletRequest request) {

        /* 수정한 댓글 변수에 담기 */
        String editComment = requestDto.getComment();

        /* 로그인 확인 */
        String token = jwtUtil.resolveToken(request);
        claims = checkToken(token);
        findUser();

        //게시글 확인
        checkBoard(boardId);

        //댓글 확인, 수정
        Comment comment = checkComment(commentId);
        checkUser(comment.getWriter(), requestDto.getUsername());
        comment.update(editComment);

        return new ResponseComment(boardId, comment);
    }

    /*
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        //유저 확인(추가 예정)

        //게시글 확인
        checkBoard(boardId);

        //댓글 확인
        Comment comment = checkComment(commentId);

        //댓글 삭제
        commentRepository.delete(comment);

    }




    /*
     * board확인
     */
    private Board checkBoard(Long id){
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_BOARD_EXCEPTION_MSG.getMsg())
        );
    }

    /*
     * comment확인
     */
    private Comment checkComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg())
        );
    }

    /*
     * token확인
     */
    private Claims checkToken(String token){
        if (token != null) {
            if (jwtUtil.validateToken(token)) {//유효한 토큰인지 검사
                return claims = jwtUtil.getUserInfoFromToken(token);// 토큰에서 사용자 정보 가져오기
            } else {//유효한 토큰이 아니면
                throw new IllegalArgumentException(TOKEN_ERROR_EXCEPTION_MSG.getMsg()); //토큰 에러 메세지 출력
            }
        }
        return claims;
    }

    /*
     * user찾기
     */
    private User findUser(){
        return userRepository.findByEmail(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_USER_EXCEPTION_MSG.getMsg())
        );
    }

    /*
     * user확인
     */
    private void checkUser(String commentUser, String requestUser){
        System.out.println("commentUser = " + commentUser);
        System.out.println("requestUser = " + requestUser);
        if(!commentUser.equals(requestUser)){
            throw new IllegalArgumentException(WRONG_COMMENT_WRITER_EXCEPTION.getMsg());
        }
    }

}
