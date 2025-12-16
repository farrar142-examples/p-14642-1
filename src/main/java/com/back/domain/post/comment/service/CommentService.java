package com.back.domain.post.comment.service;

import com.back.domain.post.comment.document.Comment;
import com.back.domain.post.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public long count(){
        return commentRepository.count();
    }

    public Comment create(String content, String author){
        Comment comment = new Comment(content, author);
        return commentRepository.save(comment);
    }

}
