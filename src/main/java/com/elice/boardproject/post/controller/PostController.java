package com.elice.boardproject.post.controller;

import com.elice.boardproject.comment.dto.CommentResponseDTO;
import com.elice.boardproject.comment.service.CommentService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    // 게시글 리스트 조회
    @GetMapping("/postList")
    public String getAllPosts(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              Model model) {

        Page<PostListResponseDTO> postPage = postService.getAllPosts(PageRequest.of(page, size));
        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("page", postPage);

        return "post/postList";
    }

    // 게시글 상세 조회
    @GetMapping("/detail/{postId}")
    public String getPostDetail(@PathVariable Long postId,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "3") int size,
                                Model model) {
        PostResponseDTO postDetail = postService.getPostDetail(postId);
        Page<CommentResponseDTO> comments = commentService.getPagedComment(postId, PageRequest.of(page, size));

        model.addAttribute("postDetail", postDetail);
        model.addAttribute("comments", comments);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "post/postDetail"; // Thymeleaf 템플릿 이름
    }

    // 게시글 작성
    @PostMapping("/newPost")
    public String createBoard(@ModelAttribute PostRequestDTO postRequestDto, RedirectAttributes redirectAttributes) {
        Long postId = postService.savePost(postRequestDto);

        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 추가되었습니다.");
        redirectAttributes.addFlashAttribute("postId", postId);

        return "redirect:/post/detail/" + postId;
    }

    // 게시글 수정
    @PostMapping("/editPost/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDTO postRequestDto, RedirectAttributes redirectAttributes) {
        postService.updatePost(postId, postRequestDto);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        return "redirect:/posts/" + postId;
    }

    // 게시글 삭제
    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }
}
