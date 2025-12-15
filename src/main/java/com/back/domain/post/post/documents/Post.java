package com.back.domain.post.post.documents;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "posts")
public class Post {
    @Id
    private String id;
    @Field(type= FieldType.Text)
    private String title;
    @Field(type= FieldType.Text)
    private String content;
    @Field(type= FieldType.Keyword)
    private String author;
    @Field(type= FieldType.Date,format= DateFormat.date_hour_minute_second_millis)
    private LocalDateTime createdAt;
    @Field(type= FieldType.Date,format= DateFormat.date_hour_minute_second_millis)
    private LocalDateTime updatedAt;
}
