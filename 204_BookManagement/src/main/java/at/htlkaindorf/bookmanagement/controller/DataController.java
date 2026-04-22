package at.htlkaindorf.bookmanagement.controller;

import at.htlkaindorf.bookmanagement.pojos.Author;
import at.htlkaindorf.bookmanagement.pojos.Book;
import at.htlkaindorf.bookmanagement.pojos.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.*;

public class DataController {
    private ObservableList<Author> authors;
    private ObservableList<Book> books;
    private ObservableList<String> bookAuthors;
    private Map<Author, Set<Book>> bookAuthorMap;

    public DataController() throws Exception {
        authors = FXCollections.observableArrayList();
        books = FXCollections.observableArrayList();
        bookAuthors = FXCollections.observableArrayList();
        bookAuthorMap = new HashMap<>();

        readBooks();
        readAuthors();
    }

    private void readBooks() throws Exception {
        books.addAll(FileIO.readUniqueBooks());
    }

    private void readAuthors() throws Exception {
        authors.addAll(FileIO.readUniqueAuthors());
    }

    public void sortBooksBy(String property, String type) throws Exception {
        Comparator<Book> cmp = switch (property) {
            case "isbn" -> Comparator.comparing(Book::getIsbn);
            case "title" -> Comparator.comparing(Book::getTitle);
            case "pages" -> Comparator.comparing(Book::getPages);
            case "year" -> Comparator.comparing(Book::getYear);
            case "genre" -> Comparator.comparing(b -> b.getGenre().name());
            default -> throw new Exception("The sorting property does not exist: " + property);
        };

        FXCollections.sort(books,
                extractSortType(type) == 'A' ? cmp : cmp.reversed());
    }

    public void sortAuthorsBy(String property, String type) throws Exception {
        Comparator<Author> cmp = switch (property) {
            case "firstname" -> Comparator.comparing(Author::getFirstName);
            case "lastname" -> Comparator.comparing(Author::getLastName);
            case "country" -> Comparator.comparing(Author::getCountry);
            case "birthyear" -> Comparator.comparing(Author::getBirthYear);
            default -> throw new Exception("The sorting property does not exist: " + property);
        };

        FXCollections.sort(authors,
                extractSortType(type) == 'A' ? cmp : cmp.reversed());
    }

    private char extractSortType(String type) throws Exception {
        if (type.equals("A")) {
            return 'A';
        }

        if (type.equals("D")) {
            return 'D';
        }

        throw new Exception("Sorting type does not exist: " + type);
    }

    public void assign(int authorIndex, ObservableList<Book> books) {
        bookAuthorMap.computeIfAbsent(authors.get(authorIndex), a -> new HashSet<>(books));
        convertMapToList();
    }

    private void convertMapToList() {
        clearAllMappings();
        for (Map.Entry<Author, Set<Book>> entry : bookAuthorMap.entrySet()) {
            bookAuthors.add(String.format(Locale.GERMAN, "%s, %s wrote [%s]",
                    entry.getKey().getLastName(), entry.getKey().getFirstName(), entry.getValue()));
        }
    }

    public void clearAllMappings() {
        bookAuthorMap.clear();
    }

    public void exportData(File file) {

    }

    public ObservableList<String> getBookAuthors() {
        return bookAuthors;
    }
}
