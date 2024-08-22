package com.elice.boardproject.comment.dto;

import com.elice.boardproject.comment.entity.Comment;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class CommentRequestDTO {
    private Long id;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                    .id(this.id)
                    .content(this.content)
                    .build();
    }
}
