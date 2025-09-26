package com.example.library.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;


@Data @NoArgsConstructor @AllArgsConstructor
@Document("loans")
public class Loan {
@Id private String id;
private String bookId;
private String memberId;
private LocalDate borrowedOn;
private LocalDate dueOn;
private int renewals; // number of times renewed
private boolean returned;
}
