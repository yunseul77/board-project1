package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Board;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class BoardRequestDto {

    private Long id;
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                    .id(this.id)
                    .title(this.title)
                    .content(this.content)
                    .build();
    }
}
