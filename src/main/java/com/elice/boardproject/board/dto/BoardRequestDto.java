package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDto {

    private String title;

    private String content;

}
