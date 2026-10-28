package at.htlkaindorf._04_bookmanagement.pojos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileIO {
    public static Set<Book> readUniqueBooks() {
        Path path = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "books.csv");
        Set<Book> books = new HashSet<>();

        try {
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");

                try {
                    Book book = new Book(
                            Genre.valueOf(tokens[3]),
                            Long.parseLong(tokens[0]),
                            tokens[1],
                            Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[4]));

                    if (checkISBN(Long.parseLong(tokens[0]))) {
                        books.add(book);
                    } else {
                        System.out.println("INVALID ISBN: " + tokens[0]);
                    }
                } catch (Exception e) {
                    System.out.println("INVALID BOOK: " + tokens[1]);
                }
            }

            return books;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkISBN(long isbn) {
        String isbnString = String.valueOf(isbn);

        if (isbnString.length() != 13) {
            return false;
        }

        if (!(isbnString.startsWith("978") || isbnString.startsWith("979"))) {
            return false;
        }

        char[] isbnCharArray = isbnString.toCharArray();
        int checkDigit = Integer.parseInt(String.valueOf(isbnString.charAt(isbnString.length() - 1)));
        int sum = 0;

        for (int i = 0; i < isbnCharArray.length - 1; i++) {
            sum += (i % 2 == 0)
                    ? Character.getNumericValue(isbnString.charAt(i))
                    : Character.getNumericValue(isbnString.charAt(i)) * 3;
        }

        return checkDigit == (10 - sum % 10);
    }

    public static Set<Author> readUniqueAuthors() {
        Path path = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "authors.csv");
        Set<Author> authors = new HashSet<>();

        try {
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");

                try {
                    authors.add(new Author(
                            tokens[0],
                            tokens[1],
                            tokens[2],
                            Integer.parseInt(tokens[3])
                    ));
                } catch (Exception e) {
                    System.out.println("INVALID AUTHOR: " + tokens[0] + " " + tokens[1]);
                }
            }

            return authors;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportMapData(Map<Author, Set<Book>> bookAuthorMap, File file) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        int authorCount = 0;

        for (Map.Entry<Author, Set<Book>> entry : bookAuthorMap.entrySet()) {
            Author author = entry.getKey();
            Set<Book> books = entry.getValue();

            sb.append("\t{\n");
            sb.append("\t\t\"firstname\" : \"").append(author.getFirstName()).append("\",\n");
            sb.append("\t\t\"lastname\" : \"").append(author.getLastName()).append("\",\n");
            sb.append("\t\t\"country\" : \"").append(author.getCountry()).append("\",\n");
            sb.append("\t\t\"birthyear\" : ").append(author.getBirthYear()).append(",\n");
            sb.append("\t\t\"books\" : [\n");

            int bookCount = 0;

            for (Book book : books) {
                sb.append("\t\t\t{\n");
                sb.append("\t\t\t\t\"isbn\" : ").append(book.getIsbn()).append(",\n");
                sb.append("\t\t\t\t\"title\" : \"").append(book.getTitle()).append("\",\n");
                sb.append("\t\t\t\t\"Genre\" : \"").append(book.getGenre()).append("\"\n");
                sb.append("\t\t\t}");

                if (++bookCount < books.size()) {
                    sb.append(",");
                }

                sb.append("\n");
            }

            sb.append("\t\t]\n");
            sb.append("\t}");

            if (++authorCount < bookAuthorMap.size()) {
                sb.append(",");
            }

            sb.append("\n");
        }

        sb.append("]");

        Files.writeString(file.toPath(), sb.toString());
    }
}
