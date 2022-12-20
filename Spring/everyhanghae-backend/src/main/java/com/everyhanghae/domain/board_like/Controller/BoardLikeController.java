package com.everyhanghae.domain.board_like.Controller;


import com.everyhanghae.domain.board_like.Service.BoardLikeService;
import com.everyhanghae.shared.response.Response;
import com.everyhanghae.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.everyhanghae.shared.response.ResponseMessage.INSERT_LIKE_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.DELETTE_LIKE_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    private final JwtService jwtService;

    /* 좋아요 등록, 취소 */
    @PostMapping("/{id}/like")
    public Response boardLike(@PathVariable Long id, HttpServletRequest servletRequest){

        Claims claims = jwtService.getTokenClaim(servletRequest);
        String email = claims.getSubject();

        boolean likeboolean = boardLikeService.boardLike(id,email);
        if (likeboolean){ // 좋아요 등록
            return new Response(INSERT_LIKE_SUCCESS_MSG);
        }else { // 좋아요 취소
            return new Response(DELETTE_LIKE_SUCCESS_MSG);
        }

    }

}
