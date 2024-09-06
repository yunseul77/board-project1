package com.elice.boardproject.comment.controller;

import com.elice.boardproject.comment.dto.CommentRequestDTO;
import com.elice.boardproject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/addComment")
    public String addComment(@PathVariable Long postId, @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.addComment(postId, commentRequestDTO);

        return "post/createPost";
    }
}
