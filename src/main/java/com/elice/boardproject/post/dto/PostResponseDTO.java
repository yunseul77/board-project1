package com.elice.boardproject.post.dto;

import com.elice.boardproject.post.Entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostResponseDTO {

    private String title;
    private String content;
    private String nickName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // postResponseDto 생성
    public PostResponseDTO(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickName = post.getUser().getNickName();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
