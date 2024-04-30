package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.dto.BoardDto;
import com.elice.boardproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // GET API
    // 게시글 리스트 출력
    @GetMapping("/list")
    public String getAllBoards(Model model) {
        List<Board> boardList = boardService.getAllBoards();
        model.addAttribute("boardList", boardList);
        return "board/board";
    }

    // 게시글 작성 폼
    @GetMapping("/form")
    public String boardForm() {
        return "board/createPost";
    }

    // 게시글 수정 폼
    @GetMapping("/updateform")
    public String boardUpdateForm(@RequestParam("id") Long id, Model model) {
        if(boardService.getById(id) != null) {
            model.addAttribute("board_id", id);
            return "/board/editPost";
        }
        return "redirect:/board/board";
    }

    // POST API
    // 게시글 작성
//    @PostMapping("/boards/new")
//    public String createBoard(@RequestBody Map<String, String> param) {
//        String title = param.get("title");
//        String content = param.get("content");
//        boardService.saveBoard(title, content);
//        return "redirect:/board/board";
//    }

    // 게시글 작성
    @PostMapping("/save")
    public String createBoard(@RequestBody BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "redirect:/board/board";
    }

    // PUT API
    @PutMapping("/boards/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boardDto.setId(id);
        return boardService.updateBoard(boardDto);
    }
    // DELETE API
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable(value = "id") Long id) {
        boardService.deleteBoard(id);
    }
}
