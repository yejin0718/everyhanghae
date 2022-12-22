package com.everyhanghae.domain.board_like.Controller;

import com.everyhanghae.domain.board_like.Service.BoardLikeService;
import com.everyhanghae.security.UserDetailsImpl;
import com.everyhanghae.shared.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.everyhanghae.shared.response.ResponseMessage.DELETTE_LIKE_SUCCESS_MSG;
import static com.everyhanghae.shared.response.ResponseMessage.INSERT_LIKE_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    /* 좋아요 등록, 취소 */
    @PostMapping("/{id}/like")
    public Response boardLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String email = userDetails.getUsername();

        boolean likeboolean = boardLikeService.boardLike(id,email);
        if (likeboolean){ // 좋아요 등록
            return new Response(INSERT_LIKE_SUCCESS_MSG);
        }else { // 좋아요 취소
            return new Response(DELETTE_LIKE_SUCCESS_MSG);
        }
    }

}
