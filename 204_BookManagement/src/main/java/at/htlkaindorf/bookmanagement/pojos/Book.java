package at.htlkaindorf.bookmanagement.pojos;

import at.htlkaindorf.bookmanagement.enums.Genre;

import java.util.Locale;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private Genre genre;
    private long isbn;
    private String title;
    private int year;
    private int pages;

    public Book(Genre genre, long isbn, String title, int year, int pages) {
        this.genre = genre;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%s (%d) - %s; %d pages in %d",
                title, isbn, genre.name(), pages, year);
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }
}
