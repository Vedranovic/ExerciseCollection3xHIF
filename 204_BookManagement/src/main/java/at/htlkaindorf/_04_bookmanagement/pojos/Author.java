package at.htlkaindorf._04_bookmanagement.pojos;

import java.util.Objects;

public class Author implements Comparable<Author> {
    private String firstName;
    private String lastName;
    private String country;
    private int birthYear;

    public Author(String firstName, String lastName, String country, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("%s %s, %d in %s", lastName, firstName, birthYear, country);
    }

    @Override
    public int compareTo(Author o) {
        if (this.lastName.equals(o.lastName)) {
            return this.firstName.compareTo(o.firstName);
        }

        return this.lastName.compareTo(o.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
