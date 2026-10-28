package at.htlkaindorf._04_bookmanagement.controller;

import at.htlkaindorf._04_bookmanagement.pojos.Author;
import at.htlkaindorf._04_bookmanagement.pojos.Book;
import at.htlkaindorf._04_bookmanagement.pojos.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataController {
    private List<Author> authors;
    private List<Book> books;
    private ObservableList<String> bookAuthors;
    private Map<Author, Set<Book>> bookAuthorMap;

    public DataController() {
        readAuthor();
        readBooks();
        bookAuthors = FXCollections.observableArrayList();
        bookAuthorMap = new HashMap<>();
    }

    private void readBooks() {
        books = new ArrayList<>(FileIO.readUniqueBooks());
    }

    private void readAuthor() {
        authors = new ArrayList<>(FileIO.readUniqueAuthors());
    }

    public void sortBooksBy(String property, String type) throws Exception {
        Comparator<Book> comparatorBook = switch (property) {
            case "isbn" -> Comparator.comparing(Book::getIsbn);
            case "title" -> Comparator.comparing(Book::getTitle);
            case "year" -> Comparator.comparing(Book::getYear);
            case "pages" -> Comparator.comparing(Book::getPages);
            case "genre" -> Comparator.comparing(book -> book.getGenre().name());
            default -> throw new Exception("The sorting property does not exist: " + property);
        };

        books.sort(extractSortType(type) == 'A' ? comparatorBook : comparatorBook.reversed());
    }

    public void sortAuthorsBy(String property, String type) throws Exception {
        Comparator<Author> comparatorAuthor = switch (property) {
            case "firstname" -> Comparator.comparing(Author::getFirstName);
            case "lastname" -> Comparator.comparing(Author::getLastName);
            case "country" -> Comparator.comparing(Author::getCountry);
            case "birthYear" -> Comparator.comparing(Author::getBirthYear);
            default -> throw new Exception("The sorting property does not exist: " + property);
        };

        authors.sort(extractSortType(type) == 'A' ? comparatorAuthor : comparatorAuthor.reversed());
    }

    private char extractSortType(String type) throws Exception {
        if (type.equals("A")) {
            return 'A';
        } else if (type.equals("D")) {
            return 'D';
        } else {
            throw new Exception("This sorting type does not exist: " + type);
        }
    }

    public void assign(int authorIndex, ObservableList<Book> books) {
        if (bookAuthorMap.containsKey(authors.get(authorIndex))) {
            for (Map.Entry<Author, Set<Book>> entry : bookAuthorMap.entrySet()) {
                if (entry.getKey().equals(authors.get(authorIndex))) {
                    entry.getValue().addAll(books);
                }
            }
        } else {
            bookAuthorMap.put(authors.get(authorIndex), new HashSet<>(books));
        }

        convertMapToList();
    }

    private void convertMapToList() {
        StringBuilder text = new StringBuilder();

        for (Map.Entry<Author, Set<Book>> entry : bookAuthorMap.entrySet()) {
            StringBuilder books = new StringBuilder();

            for (Book book : entry.getValue()) {
                books.append(book.getTitle()).append(", ");
            }
            books.delete(books.length() - 2, books.length());

            text.append(String.format("%s, %s wrote [%s]",
                    entry.getKey().getLastName(), entry.getKey().getFirstName(), books)).append("\n");
        }

        bookAuthors.setAll(text.toString());
    }

    public void clearAllMappings() {
        bookAuthorMap.clear();
        convertMapToList();
    }

    public void exportData(File file) throws IOException {
        FileIO.exportMapData(bookAuthorMap, file);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }

    public ObservableList<String> getBookAuthors() {
        return bookAuthors;
    }
}
