package com.elice.boardproject.board.service;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardRequestDto;
import com.elice.boardproject.board.repository.BoardRepository;
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

    public Board saveBoard(BoardRequestDto boardRequestDto) {
        return boardRepository.save(boardRequestDto.toEntity());
    }

    // 게시글 수정
    public Board updateBoard(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardRequestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        return board;
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        boardRepository.delete(board);
    }
}
