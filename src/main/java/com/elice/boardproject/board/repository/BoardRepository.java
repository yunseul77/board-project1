package com.elice.boardproject.board.repository;

import com.elice.boardproject.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //전체 리스트 조회
    List<Board> findAllByOrderByModifiedAtDesc();

}
