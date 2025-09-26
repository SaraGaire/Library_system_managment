package com.example.library.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data @NoArgsConstructor @AllArgsConstructor
@Document("members")
public class Member {
@Id private String id;
private String universityId; // student/staff number
private String name;
private String email;
}
