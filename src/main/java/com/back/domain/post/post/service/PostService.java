package com.back.domain.post.post.service;

import com.back.domain.post.post.documents.Post;
import com.back.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public long count(){
        return postRepository.count();
    }
    public Post create(String title, String content, String author){
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }
    public Post update(Post post, String title, String content){
        if (title != null) {
            post.setTitle(title);
        }
        if (content != null) {
            post.setContent(content);
        }

        // Note: In a real application, you would typically set the ID of the updatedPost to match the original post's ID.
        return postRepository.save(post);
    }
    public void delete(Post post){
        postRepository.delete(post);
    }
}
