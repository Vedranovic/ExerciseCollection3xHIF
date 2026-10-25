import pojos.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main() {
        List<Student> students = readStudent();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("--- Set Experiment ---");
            System.out.println("(1)     HashSet");
            System.out.println("(2)     TreeSet");
            System.out.println("(3)     LinkedHashSet");
            System.out.println("(4)     End");
            System.out.println();
            System.out.print("Selection: ");
            choice = scanner.nextInt();
            System.out.println("--------------------------------");

            switch (choice) {
                case 1 -> {
                    long start = System.nanoTime();
                    SetMethods methods = new HashSetImpl();

                    methods.insert(students);

                    System.out.println("ELEMENTS AFTER INSERT: " + methods.getSize());
                    System.out.println();
                    System.out.println("DATA AFTER INSERT");
                    System.out.println(methods.collectSetData());
                    System.out.println();
                    System.out.println("DATA AFTER SORT");

                    students = methods.sort();

                    printStudents(students);
                    System.out.println();

                    long end = System.nanoTime();
                    long complete = end - start;
                    System.out.println("NEEDED TIME: " + complete + " ns");
                }
                case 2 -> {
                    long start = System.nanoTime();
                    SetMethods methods = new TreeSetImpl();
                    methods.insert(students);

                    System.out.println("ELEMENTS AFTER INSERT: " + methods.getSize());
                    System.out.println();
                    System.out.println("DATA AFTER INSERT");
                    System.out.println(methods.collectSetData());
                    System.out.println();
                    System.out.println("DATA AFTER SORT");

                    students = methods.sort();

                    printStudents(students);
                    System.out.println();

                    long end = System.nanoTime();
                    long complete = end - start;
                    System.out.println("NEEDED TIME: " + complete + " ns");
                }
                case 3 -> {
                    long start = System.nanoTime();
                    SetMethods methods = new LinkedHashSetImpl();

                    methods.insert(students);

                    System.out.println("ELEMENTS AFTER INSERT: " + methods.getSize());
                    System.out.println();
                    System.out.println("DATA AFTER INSERT");
                    System.out.println(methods.collectSetData());
                    System.out.println();
                    System.out.println("DATA AFTER SORT");

                    students = methods.sort();

                    printStudents(students);
                    System.out.println();

                    long end = System.nanoTime();
                    long complete = end - start;
                    System.out.println("NEEDED TIME: " + complete + " ns");
                }
            }
        }
    }

    private static List<Student> readStudent() {
        try {
            List<Student> students = new ArrayList<>();
            FileReader fr = new FileReader(
                    Path.of(System.getProperty("user.dir"), "src", "files", "students.csv").toFile()
            );
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                students.add(new Student(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3])));
            }

            return students;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}
