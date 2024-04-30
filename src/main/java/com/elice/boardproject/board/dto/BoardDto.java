package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.entity.Board;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class BoardDto {

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
