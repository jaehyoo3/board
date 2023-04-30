package com.test.board.service.dto;

public class RelatedPostDTO {
    private Long id;
    private Long boardId;
    private Float similarity;

    public RelatedPostDTO(Long id, Long boardId, Float similarity) {
        this.id = id;
        this.boardId = boardId;
        this.similarity = similarity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Float similarity) {
        this.similarity = similarity;
    }
}
