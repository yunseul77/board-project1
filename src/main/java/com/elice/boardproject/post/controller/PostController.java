package com.elice.boardproject.post.controller;

import com.elice.boardproject.comment.dto.CommentResponseDTO;
import com.elice.boardproject.comment.service.CommentService;
import com.elice.boardproject.post.Entity.Post;
import com.elice.boardproject.post.dto.PostDetailResponseDTO;
import com.elice.boardproject.post.dto.PostListResponseDTO;
import com.elice.boardproject.post.dto.PostRequestDTO;
import com.elice.boardproject.post.dto.PostResponseDTO;
import com.elice.boardproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    // 게시글 조회
    @GetMapping("/postList")
    public ResponseEntity<Page<PostListResponseDTO>> getAllPosts(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<PostListResponseDTO> postPage = postService.getAllPosts(PageRequest.of(page, size));
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    // 게시글 상세 조회
    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostDetailResponseDTO> getPostDetail(@PathVariable Long postId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        PostResponseDTO postDetail = postService.getPostDetail(postId);
        Page<CommentResponseDTO> comments = commentService.getPagedComment(postId, PageRequest.of(page, size));

        PostDetailResponseDTO postDetailResponse = new PostDetailResponseDTO(postDetail, comments);

        return new ResponseEntity<>(postDetailResponse, HttpStatus.OK);
    }

    // 게시글 작성
    @PostMapping("/newPost")
    public ResponseEntity<String> createBoard(@RequestBody PostRequestDTO postRequestDto) {
        Long postId = postService.savePost(postRequestDto);
        return ResponseEntity.ok("게시글이 성공적으로 추가되었습니다." + postId);
    }

    // 게시글 수정
    @PatchMapping("/updatePost/{postId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/deletePost/{postId)")
    public ResponseEntity<String> deleteBoard(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }
}