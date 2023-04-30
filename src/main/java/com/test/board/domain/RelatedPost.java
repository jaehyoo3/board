package com.test.board.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "related_post")
public class RelatedPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_post_id", nullable = false)
    private Post relatedPost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getRelatedPost() {
        return relatedPost;
    }

    public void setRelatedPost(Post relatedPost) {
        this.relatedPost = relatedPost;
    }

}