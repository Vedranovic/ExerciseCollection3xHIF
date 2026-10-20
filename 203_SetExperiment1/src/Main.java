import pojos.HashSetImpl;
import pojos.LinkedHashSetImpl;
import pojos.Student;
import pojos.TreeSetImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;

        while (toContinue) {
            System.out.println("--- Set Experiment ---");
            System.out.println("(1)     HashSet");
            System.out.println("(2)     TreeSet");
            System.out.println("(3)     LinkedHashSet");
            System.out.println("(4)     End");
            System.out.println();
            System.out.print("Selection: ");
            int choice = scanner.nextInt();

            System.out.println("---------------------------------");

            switch (choice) {
                case 1:
                    long start1 = System.nanoTime();
                    List<Student> hashList = Main.readStudentData();
                    HashSetImpl hashSet = new HashSetImpl();

                    System.out.println("ELEMENTS INSERTED: " + hashList.size());
                    System.out.println("DATA AFTER INSERT");

                    hashSet.insert(hashList);
                    hashSet.printSet();
                    System.out.println();

                    System.out.println("DATA AFTER SORT");

                    List<Student> sortedHashList = hashSet.sort();
                    Main.printStudents(sortedHashList);
                    long end1 = System.nanoTime();
                    long dauer1 = end1 - start1;
                    System.out.println("NEEDED TIME: " + dauer1 + " ns");
                    System.out.println();
                    break;
                case 2:
                    long start2 = System.nanoTime();
                    List<Student> treeList = Main.readStudentData();
                    TreeSetImpl treeSet = new TreeSetImpl();

                    System.out.println("ELEMENTS INSERTED: " + treeList.size());
                    System.out.println("DATA AFTER INSERT");

                    treeSet.insert(treeList);
                    treeSet.printSet();
                    System.out.println();

                    System.out.println("DATA AFTER SORT");

                    List<Student> sortedTreeList = treeSet.sort();
                    Main.printStudents(sortedTreeList);
                    long end2 = System.nanoTime();
                    long dauer2 = end2 - start2;
                    System.out.println("NEEDED TIME: " + dauer2 + " ns");
                    System.out.println();
                    break;
                case 3:
                    long start3 = System.nanoTime();
                    List<Student> linkedHashList = Main.readStudentData();
                    LinkedHashSetImpl linkedHashSet = new LinkedHashSetImpl();

                    System.out.println("ELEMENTS INSERTED: " + linkedHashList.size());
                    System.out.println("DATA AFTER INSERT");

                    linkedHashSet.insert(linkedHashList);
                    linkedHashSet.printSet();
                    System.out.println();

                    System.out.println("DATA AFTER SORT");

                    List<Student> sortedLinkedHashList = linkedHashSet.sort();
                    Main.printStudents(sortedLinkedHashList);
                    long end3 = System.nanoTime();
                    long dauer3 = end3 - start3;
                    System.out.println("NEEDED TIME: " + dauer3 + " ns");
                    System.out.println();
                    break;
                default:
                    toContinue = false;
                    break;
            }
        }
    }

    private static List<Student> readStudentData() {
        Path path = Path.of(System.getProperty("user.dir"), "src", "resources", "students.csv");
        List<Student> students = new ArrayList<>();

        try {
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                students.add(new Student(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3])));
            }

            br.close();
            fr.close();
        } catch (IOException ie) {
            throw new RuntimeException();
        }

        return students;
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}
