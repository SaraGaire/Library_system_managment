package com.example.library.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data @NoArgsConstructor @AllArgsConstructor
@Document("holds")
@CompoundIndex(def = "{'bookId':1,'memberId':1}", unique = true)
public class Hold {
@Id private String id;
private String bookId;
private String memberId;
private LocalDateTime placedAt;
}
