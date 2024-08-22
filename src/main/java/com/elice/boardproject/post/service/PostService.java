package com.elice.boardproject.post.service;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.Entity.Post;
import com.elice.boardproject.post.dto.PostListResponseDTO;
import com.elice.boardproject.post.dto.PostRequestDTO;
import com.elice.boardproject.post.dto.PostResponseDTO;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 조회
    public Page<PostListResponseDTO> getAllPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);

        List<PostListResponseDTO> postListResponse = postPage.getContent().stream()
                .map(PostListResponseDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(postListResponse, pageable, postPage.getTotalElements());
    }

    // 게시글 상세 조회
    public PostResponseDTO getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return new PostResponseDTO(post);
    }

    // 게시글 생성
    public Long savePost(PostRequestDTO postRequestDto) {
        Post newPost = postRepository.save(postRequestDto.toEntity());
        return newPost.getId();
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDTO postRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        postRepository.save(post);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }
}
