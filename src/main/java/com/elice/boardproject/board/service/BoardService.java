package com.elice.boardproject.board.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.dto.BoardDto;
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

    // 아이디 조회
    public Board getById(Long id) {
        return boardRepository.getById(id);
    }

    // 게시글 생성
    public Board saveBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity());
    }

    // 게시글 수정
    public Board updateBoard(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        return board;
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        boardRepository.delete(board);
    }
}
