package pojos;

import interfaces.SetMethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

public class LinkedHashSetImpl implements SetMethods {
    private LinkedHashSet<Student> studentSet;

    public LinkedHashSetImpl() {
        studentSet = new LinkedHashSet<>();
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
