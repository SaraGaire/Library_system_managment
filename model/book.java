import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data @NoArgsConstructor @AllArgsConstructor
@Document("books")
public class Book {
@Id private String id;
private String isbn;
private String title;
private String author;
private int copiesTotal;
private int copiesAvailable;
}
