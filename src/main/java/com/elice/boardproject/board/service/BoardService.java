package com.elice.boardproject.board.service;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardRequestDto;
import com.elice.boardproject.board.dto.BoardResponseDto;
import com.elice.boardproject.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 모든 게시글 리스트 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 게시글 생성
    public Board saveBoard(String title, String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        return boardRepository.save(board);
    }

    // 게시글 수정
    public Board updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.update(boardRequestDto);
        return board;
    }
}
