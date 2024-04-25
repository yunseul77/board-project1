package com.elice.boardproject.board.repository;

import com.elice.boardproject.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 전체 게시글 조회
    List<Board> findAll();

    // 전체 게시글 리스트 조회 (수정일 내림차순)
    List<Board> findAllByOrderByModifiedAtDesc();

    Optional<Board> findById(Long id);
    // 게시글 작성
    Board save(Board board);

    // 게시글 삭제
    void delete(Board board);
}
