package pojos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TreeSetImpl extends SetMethods {
    private TreeSet<Student> treeSet = new TreeSet<>(getStudentSet());

    @Override
    public String collectSetData() {
        StringBuilder output = new StringBuilder();

        for (Student student : treeSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }

    @Override
    public List<Student> sort() {
        List<Student> students = new ArrayList<>(treeSet);

        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));

        return students;
    }

    @Override
    public void insert(List<Student> students) {
        treeSet.addAll(students);
    }

    @Override
    public int getSize() {
        return treeSet.size();
    }
}
