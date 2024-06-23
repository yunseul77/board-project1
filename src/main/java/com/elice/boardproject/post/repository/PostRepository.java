package com.elice.boardproject.post.repository;

import com.elice.boardproject.post.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 전체 게시글 조회
    Page<Post> findAll(Pageable pageable);
}
