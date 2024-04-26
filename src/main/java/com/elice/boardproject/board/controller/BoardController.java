package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardRequestDto;
import com.elice.boardproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // GET API
    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        return "board/boards";
    }

    // POST API
    @PostMapping("/boards/new")
    public Board createBoard(@RequestBody Map<String, String> param) {
        String title = param.get("title");
        String content = param.get("content");
        return boardService.saveBoard(title, content);
    }

    @PostMapping("/boards/new")
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.saveBoard(boardRequestDto);
    }

    // PUT API
    @PutMapping("/boards/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        boardRequestDto.setId(id);
        return boardService.updateBoard(boardRequestDto);
    }
    // DELETE API
    @DeleteMapping("/board/{id)")
    public void deleteBoard(@PathVariable(value = "id") Long id) {
        boardService.deleteBoard(id);
    }
}
