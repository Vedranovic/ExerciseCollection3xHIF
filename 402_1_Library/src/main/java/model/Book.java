package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @NonNull
    private Author author;
    @NonNull
    private Genre genre;
    private Long isbn;
    @NonNull
    private String title;
}
