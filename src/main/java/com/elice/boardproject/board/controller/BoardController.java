package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.dto.BoardDto;
import com.elice.boardproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // GET API
    // 게시글 리스트 출력
    @GetMapping("/list")
    public String postList(Model model) {
        List<Board> postList =  boardService.postList();
        model.addAttribute("postList", postList);
        return "/postList";
    }

    // 게시글 출력 폼
    @GetMapping("/read")
    public String readForm(@RequestParam Long id,  Model model) {
        model.addAttribute("read", boardService.getPostById(id));
        return "/post";
    }

    // 게시글 작성 폼
    @GetMapping("/write")
    public String writeForm() {
        return "/createPost";
    }

    // 게시글 수정 폼
    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        if(boardService.getById(id) != null) {
            model.addAttribute("boardId", id);
            return "editPost";
        }
        return "redirect:/postList";
    }

    // POST API
    // 게시글 작성
    @PostMapping("/write")
    public String createBoard(@RequestBody BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "/postList";
    }

    // PUT API
    @PutMapping("/updated")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boardDto.setId(id);
        return boardService.updateBoard(boardDto);
    }
    // DELETE API
    @DeleteMapping
    public void deleteBoard(@PathVariable(value = "id") Long id) {
        boardService.deleteBoard(id);
    }
}
