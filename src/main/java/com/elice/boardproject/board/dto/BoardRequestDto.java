package com.elice.boardproject.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDto {

    private String title;

    private String content;
}
