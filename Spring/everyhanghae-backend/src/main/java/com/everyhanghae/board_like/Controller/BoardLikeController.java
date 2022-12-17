package com.everyhanghae.board_like.Controller;

import com.everyhanghae.board_like.Service.BoardLikeService;
import com.everyhanghae.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.everyhanghae.common.response.ResponseMessage.INSERT_LIKE_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    /* 좋아요 등록, 취소 */
    @PostMapping("/{id}/like")
    public Response boardLike(@PathVariable Long id){
        boardLikeService.boardLike(id);
        return new Response(INSERT_LIKE_SUCCESS_MSG);
    }

}
