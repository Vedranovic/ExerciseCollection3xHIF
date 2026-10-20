package pojos;

import interfaces.SetMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TreeSetImpl implements SetMethods {
    private TreeSet<Student> studentSet;

    public TreeSetImpl() {
        studentSet = new TreeSet<>();
    }

    @Override
    public void printSet() {
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }

    @Override
    public List<Student> sort() {
        return new ArrayList<>(studentSet);
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
