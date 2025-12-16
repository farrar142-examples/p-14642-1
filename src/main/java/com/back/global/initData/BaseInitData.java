package com.back.global.initData;

import com.back.domain.post.comment.service.CommentService;
import com.back.domain.post.post.documents.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final CommentService commentService;
    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            work1();
            work2();
            work3();
            work4();
            work5();
        };
    }
    private void work1(){
        log.debug("Post entity 개수: {}",postService.count());
        if (postService.count() == 0){
            log.debug("Post entity 데이터 초기화 시작");
            for (int i = 1; i <= 10; i++) {
                String title = "Sample Post Title " + i;
                String content = "This is the content of sample post number " + i + ".";
                String author = "Author" + i;
                postService.create(title, content, author);
            }
            log.debug("Post entity 데이터 초기화 완료. 총 개수: {}", postService.count());
        } else {
            log.debug("Post entity 데이터 초기화 불필요");
        }
    }
    private void work2(){
        log.debug("저장된 Post entity 조회 시작");
        for (String postId : postService.findAll().stream().map(Post::getId).toList()) {
            Post post = postService.findById(postId).orElseThrow();
            log.debug("Post ID: {}, Title: {}, Author: {}", post.getId(), post.getTitle(), post.getAuthor());
        }
    }

    private void work3(){
        for (Post post: postService.findAll()){
            Post updatedPost = postService.update(post, post.getTitle() + " (Updated)", post.getContent() + " This content has been updated.");
            log.debug("Updated Post ID: {}, New Title: {}, New Content: {}", updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent());
        }
    }
    private void work4(){
        log.debug("전체 Post entity 개수: {}",postService.count());
        for (Post post: postService.findAll()){
            log.debug("Post ID: {}, Title: {}, Author: {}", post.getId(), post.getTitle(), post.getAuthor());
            postService.delete(post);
        }
        log.debug("삭제 후 전체 Post entity 개수: {}",postService.count());
    }

    private void work5(){
        log.debug("Comment entity 개수: {}",commentService.count());
        if (postService.count()==0){
            log.debug("Post entity 개수가 0 입니다");
            log.debug("Post entity 데이터 초기화 시작");
            for (int i = 1; i <= 10; i++) {
                String title = "Sample Post Title " + i;
                String content = "This is the content of sample post number " + i + ".";
                String author = "Author" + i;
                postService.create(title, content, author);
            }
            log.debug("Post entity 데이터 초기화 완료. 총 개수: {}", postService.count());
        }
        if (commentService.count() == 0){
            log.debug("Comment entity 데이터 초기화 시작");
            for (Post post: postService.findAll()){
                for (int j = 1; j <= 5; j++) {
                    String content = "This is comment number " + j + " for post ID " + post.getId() + ".";
                    String author = "Commenter" + j;
                    commentService.create(content, author);
                }
            }
            log.debug("Comment entity 데이터 초기화 완료. 총 개수: {}", commentService.count());
        } else {
            log.debug("Comment entity 데이터 초기화 불필요");
        }
    }
}
