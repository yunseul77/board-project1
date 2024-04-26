package com.elice.boardproject.board.domain;

import com.elice.boardproject.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Builder
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
}
