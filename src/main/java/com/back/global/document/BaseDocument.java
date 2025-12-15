package com.back.global.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class BaseDocument {
    @Id
    private String id;
    @Field(type= FieldType.Date,format= DateFormat.date_hour_minute_second_millis)
    @CreatedBy
    private LocalDateTime createdAt;
    @Field(type= FieldType.Date,format= DateFormat.date_hour_minute_second_millis)
    @LastModifiedBy
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseDocument that = (BaseDocument) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
