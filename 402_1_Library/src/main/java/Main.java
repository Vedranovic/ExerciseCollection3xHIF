import model.Author;
import model.Book;
import model.Genre;
import model.Stars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public void readData() throws IOException {
        List<String> information = Files.lines(Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "books.csv"))
                .skip(1)
                .toList();
        List<Book> bookList = new ArrayList<>();
        Set<Author> authorSet = new HashSet<>();
        Set<Genre> genreSet = new HashSet<>();
        Set<Stars> starsSet = new HashSet<>();

        information.forEach(info -> {
            String[] tokens = info.split(",");

            authorSet.add(Author.builder()
                    .firstname(tokens[2])
                    .lastname(tokens[3])
                    .bookList(new ArrayList<>())
                    .build());
            genreSet.add(Genre.builder()
                    .name(tokens[1])
                    .build());
            starsSet.add(Stars.builder()
                    .value(tokens[4])
                    .build());
            bookList.add(Book.builder()
                    .title(tokens[0])
                    .genre(genreSet.stream()
                            .filter(genre -> genre.getName().equals(tokens[1]))
                            .findFirst()
                            .get())
                    .author(authorSet.stream()
                            .filter(author -> author.equals(Author.builder()
                                            .firstname(tokens[2])
                                            .lastname(tokens[3])
                                            .bookList(new ArrayList<>())
                                            .build()))
                            .findFirst()
                            .get())
                    .build());
        });

        authorSet.forEach(author -> author.getBookList().addAll(
                bookList.stream()
                        .filter(book -> book.getAuthor().equals(author))
                        .toList()
        ));

        bookList.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.readData();
    }
}
