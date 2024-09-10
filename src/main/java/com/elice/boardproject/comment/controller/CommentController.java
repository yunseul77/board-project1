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
    @PostMapping("/{postId}/addComment")
    public String addComment(@PathVariable Long postId, @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.addComment(postId, commentRequestDTO);

        return "post/createComment";
    }

    // 댓글 수정
    @PatchMapping("/{commentId}/updateComment")
    public String updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.updateComment(commentId, commentRequestDTO);

        return "post/editComment";
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}/deleteComment")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return "post/deleteComment";
    }
}
