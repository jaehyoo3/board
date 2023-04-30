package com.test.board.service;

import com.test.board.domain.Post;
import com.test.board.domain.RelatedPost;
import com.test.board.repository.PostRepository;
import com.test.board.repository.RelatedPostRepository;
import com.test.board.service.dto.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final RelatedPostRepository relatedPostRepository;

    public PostService(PostRepository postRepository, RelatedPostRepository relatedPostRepository) {
        this.postRepository = postRepository;
        this.relatedPostRepository = relatedPostRepository;
    }

    public Post save(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

        List<Post> relatedPosts = findRelatedPosts(post);
        List<RelatedPost> relatedPostEntities = new ArrayList<>();

        for (Post relatedPost : relatedPosts) {
            RelatedPost relatedPostEntity = new RelatedPost();
            relatedPostEntity.setPost(post);
            relatedPostEntity.setRelatedPost(relatedPost);
            relatedPostEntities.add(relatedPostEntity);
//            relatedPostRepository.save(relatedPostEntity);
        }

        post.setRelatedPosts(relatedPostEntities);

        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    private List<Post> findRelatedPosts(Post post) {
        String[] words = post.getContent().split("\\W+");
        System.out.println(Arrays.toString(words));
        List<Post> allBoards = postRepository.findAll();

        List<String> commonWords = findCommonWords(allBoards, 0.0);
        System.out.println("commonWords: " + commonWords);

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            if (!commonWords.contains(word)) {
                for (Post otherBoard : allBoards) {
                    if (otherBoard.getId() != post.getId() && otherBoard.getContent().contains(word)) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        System.out.println("wordCountMap: " + wordCountMap);

        List<Post> relatedBoards = new ArrayList<>();
        for (Post otherBoard : allBoards) {
            if (otherBoard.getId() != post.getId()) {
                int count = 0;
                for (String word : wordCountMap.keySet()) {
                    if (otherBoard.getContent().contains(word)) {
                        count += wordCountMap.get(word);
                    }
                }
                System.out.println("count:" + count);
                System.out.println("size: " + wordCountMap.size() * 0.4);
                if (count >= 2 && count <= wordCountMap.size() * 0.4) {
                    relatedBoards.add(otherBoard);
                }
            }
        }
        System.out.println("relatedBoards: " + relatedBoards);

        relatedBoards.sort((o1, o2) -> {
            int count1 = 0;
            for (String word : wordCountMap.keySet()) {
                if (o1.getContent().contains(word)) {
                    count1 += wordCountMap.get(word);
                }
            }

            int count2 = 0;
            for (String word : wordCountMap.keySet()) {
                if (o2.getContent().contains(word)) {
                    count2 += wordCountMap.get(word);
                }
            }

            return Integer.compare(count2, count1);
        });

        return relatedBoards;
    }

    private List<String> findCommonWords(List<Post> posts, double threshold) {
        List<String> commonWords = new ArrayList<>();

        Map<String, Integer> countMap = new HashMap<>();
        for (Post post : posts) {
            String[] words = post.getContent().split("\\W+");
            for (String word : words) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        int totalWords = 0;
        for (int count : countMap.values()) {
            totalWords += count;
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            double freq = (double) entry.getValue() / totalWords;
            if (freq > threshold) {
                commonWords.add(entry.getKey());
            }
        }

        return commonWords;
    }
}