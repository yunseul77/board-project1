package com.elice.boardproject.post.dto;

import com.elice.boardproject.post.Entity.Post;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class PostRequestDTO {

    private Long id;
    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
                    .id(this.id)
                    .title(this.title)
                    .content(this.content)
                    .build();
    }
}
