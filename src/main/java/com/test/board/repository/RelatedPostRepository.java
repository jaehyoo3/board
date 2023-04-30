package com.test.board.repository;

import com.test.board.domain.RelatedPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedPostRepository extends JpaRepository<RelatedPost, Long> {

}
