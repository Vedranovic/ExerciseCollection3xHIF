package at.htlkaindorf.bookmanagement.pojos;

import at.htlkaindorf.bookmanagement.enums.Genre;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileIO {
    public static Set<Book> readUniqueBooks() throws Exception {
        Set<Book> bookSet = new HashSet<>();
        InputStreamReader isr = new InputStreamReader(
                FileIO.class.getResourceAsStream("/files/books.csv")
        );
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            try {
                String[] tokens = line.split(";");

                long isbn = Long.parseLong(tokens[0]);

                if (!checkISBN(isbn)) {
                    throw new Exception("Invalid ISBN:");
                }

                String title = tokens[1];
                int year = Integer.parseInt(tokens[2]);
                Genre genre = Genre.valueOf(tokens[3]);
                int pages = Integer.parseInt(tokens[4]);

                bookSet.add(new Book(genre, isbn, title, year, pages));
            } catch (Exception e) {
                throw new Exception("Invalid ISBN!");
            }
        }

        return bookSet;
    }

    private static boolean checkISBN(long isbn) {
        String isbnStr = isbn + "";
        int sum = 0;

        if (isbnStr.length() != 13) {
            return false;
        }

        if (!(isbnStr.startsWith("978") || isbnStr.startsWith("979"))) {
            return false;
        }

        for (int i = 0; i < isbnStr.length() - 1; i++) {
            sum += (i % 2 == 0)
                    ? Character.getNumericValue(isbnStr.charAt(i))
                    : Character.getNumericValue(isbnStr.charAt(i)) * 3;
        }

        return (sum + (10 - (sum % 10))) % 10 == 0;
    }

    public static Set<Author> readUniqueAuthors() throws Exception {
        Set<Author> authorSet = new HashSet<>();
        InputStreamReader isr = new InputStreamReader(
                FileIO.class.getResourceAsStream("/files/author.csv")
        );
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();
        line = br.readLine();

        while ((line = br.readLine()) != null) {
            try {
                String[] tokens = line.split(";");

                String firstName = tokens[0];
                String lastName = tokens[1];
                String country = tokens[2];
                int birthYear = Integer.parseInt(tokens[3]);

                authorSet.add(new Author(firstName, lastName, country, birthYear));
            } catch (Exception e) {
                throw new Exception("Invalid!");
            }
        }

        return authorSet;
    }

    public static void exportMapData(Map<Author, Set<Book>> bookAuthorMap, File file) {

    }
}
