package com.test.board.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<RelatedPostDTO> relatedBoards;

    public PostDTO(Long id, String title, String content, LocalDateTime createdAt, List<RelatedPostDTO> relatedBoards) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.relatedBoards = relatedBoards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<RelatedPostDTO> getRelatedBoards() {
        return relatedBoards;
    }

    public void setRelatedBoards(List<RelatedPostDTO> relatedBoards) {
        this.relatedBoards = relatedBoards;
    }
}
