package com.back.global.initData;

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
    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            work1();
            work2();
            work3();
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
}
