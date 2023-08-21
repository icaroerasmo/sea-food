package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentBase {
    @Id
    protected String id;
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    protected LocalDateTime createdAt;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    protected LocalDateTime updatedAt;
}
