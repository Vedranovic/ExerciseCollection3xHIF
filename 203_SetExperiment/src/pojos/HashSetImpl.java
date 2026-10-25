package pojos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class HashSetImpl extends SetMethods {
    private HashSet<Student> hashSet = new HashSet<>();

    @Override
    public String collectSetData() {
        StringBuilder output = new StringBuilder();

        for (Student student : hashSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }

    @Override
    public List<Student> sort() {
        List<Student> students = new ArrayList<>(hashSet);

        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));

        return students;
    }

    @Override
    public void insert(List<Student> students) {
        hashSet.addAll(students);
    }

    @Override
    public int getSize() {
        return hashSet.size();
    }
}
