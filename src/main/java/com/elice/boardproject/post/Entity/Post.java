package com.elice.boardproject.post.Entity;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.user.entity.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "post")
@Builder
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}
