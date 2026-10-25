package pojos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

public class LinkedHashSetImpl extends SetMethods {
    private LinkedHashSet<Student> linkedHashSet = new LinkedHashSet<>();

    @Override
    public String collectSetData() {
        StringBuilder output = new StringBuilder();

        for (Student student : linkedHashSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }

    @Override
    public List<Student> sort() {
        List<Student> students = new ArrayList<>(linkedHashSet);

        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));

        return students;
    }

    @Override
    public void insert(List<Student> students) {
        linkedHashSet.addAll(students);
    }

    @Override
    public int getSize() {
        return linkedHashSet.size();
    }
}
