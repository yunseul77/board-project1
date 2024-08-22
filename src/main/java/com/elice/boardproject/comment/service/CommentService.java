package com.elice.boardproject.comment.service;

import com.elice.boardproject.comment.dto.CommentRequestDTO;
import com.elice.boardproject.comment.dto.CommentResponseDTO;
import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 페이지네이션
    public Page<CommentResponseDTO> getPagedComment(Long postId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);

        List<CommentResponseDTO> commentResonseList = comments.getContent()
                                                            .stream()
                                                            .map(CommentResponseDTO::new)
                                                            .collect(Collectors.toList());

        return new PageImpl<>(commentResonseList, pageable, comments.getTotalElements());
    }

    // 댓글 추가
//    public Long addComment(Long postId, CommentRequestDTO commentRequestDTO) {
//
//    }
}
