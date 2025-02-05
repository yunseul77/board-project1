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
import org.springframework.transaction.annotation.Transactional;

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

    // 댓글 작성
    public void addComment(Long postId, CommentRequestDTO commentRequestDTO) {
        postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        Comment newComment = commentRequestDTO.toEntity();
        commentRepository.save(newComment);
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentRequestDTO commentRequestDTO) {
        Comment updatedComment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));

        updatedComment.setContent(commentRequestDTO.getContent());
        commentRepository.save(updatedComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}
