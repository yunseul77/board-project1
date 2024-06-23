package com.elice.boardproject.post.dto;

import com.elice.boardproject.comment.dto.CommentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDetailResponseDTO {

    private PostResponseDTO postDetail;
    private Page<CommentResponseDTO> comments;
}
