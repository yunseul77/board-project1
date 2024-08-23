package com.elice.boardproject.post.dto;

import com.elice.boardproject.post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostListResponseDTO {
    private Long id;
    private String title;
    private String nickName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostListResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
//        this.nickName = post.getUser().getNickName();
        this.createdAt = post.getModifiedAt();
        this.modifiedAt = post.getCreatedAt();
    }
}
