package com.test.board.repository;

import com.test.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByOrderByCreatedAtDesc(); // 게시글 목록 조회
    List<Post> findByContentContaining(String keyword); // 특정 키워드를 포함하는 게시글 조회
}