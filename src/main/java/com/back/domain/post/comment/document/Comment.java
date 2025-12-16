package com.back.domain.post.comment.document;

import com.back.global.document.BaseDocument;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "comments")
@NoArgsConstructor
@Data
public class Comment extends BaseDocument {
    private String content;
    private String author;

    public Comment(String content, String author){
        this.content = content;
        this.author = author;
    }
}
