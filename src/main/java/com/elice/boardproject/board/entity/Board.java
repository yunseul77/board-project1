package com.elice.boardproject.board.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;


}
