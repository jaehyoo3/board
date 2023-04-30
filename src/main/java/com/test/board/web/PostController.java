package com.test.board.web;

import com.test.board.domain.Post;
import com.test.board.service.PostService;
import com.test.board.service.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getBoards() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getBoardWithRelatedBoards(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Post> createBoard(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.save(postDTO));
    }

}