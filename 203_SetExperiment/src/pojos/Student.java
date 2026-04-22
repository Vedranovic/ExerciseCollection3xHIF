package pojos;

import java.util.Locale;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%15s, %15s (%30s) - %7d", lastName.toUpperCase(), firstName, email, age);
    }

    public static void main(String[] args) {
        Student student = new Student("Vedran", "Popic", "vedran.popic27@gmail.com", 18);

        System.out.println(student);
    }

    @Override
    public int compareTo(Student o) {
        if (this.lastName.equals(o.lastName)) {
            return this.firstName.compareTo(o.firstName);
        }

        return this.lastName.compareTo(o.firstName);
    }
}
