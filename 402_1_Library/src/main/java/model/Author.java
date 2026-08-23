package model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private List<Book> bookList;
    private Long id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
}
