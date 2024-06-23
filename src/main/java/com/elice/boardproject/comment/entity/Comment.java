package com.elice.boardproject.comment.entity;

import com.elice.boardproject.post.Entity.BaseEntity;
import com.elice.boardproject.post.Entity.Post;
import com.elice.boardproject.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Builder
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 600, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "comment")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment")
    private User user;
}
