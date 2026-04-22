package pojos;

import interfaces.SetMethods;

import java.util.*;

public class HashSetImpl implements SetMethods {
    private HashSet<Student> studentSet;

    public HashSetImpl() {
        studentSet = new HashSet<>();
    }

    @Override
    public void printSet() {
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }

    @Override
    public List<Student> sort() {
        List<Student> sortedSet = new ArrayList<>(studentSet);

        sortedSet.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));

        return sortedSet;
    }

    @Override
    public void insert(List<Student> students) {
        studentSet.addAll(students);
    }

    @Override
    public int getSize() {
        return studentSet.size();
    }
}
