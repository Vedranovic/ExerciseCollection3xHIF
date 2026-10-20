package pojos;

import java.util.*;

public abstract class SetMethods {
    private Set<Student> studentSet;

    public SetMethods() {
        this.studentSet = Collections.emptySet();
    }

    public abstract String collectSetData();

    public List<Student> sort() {
        List<Student> students = new ArrayList<>(studentSet);

        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));

        return students;
    }

    public void insert(List<Student> students) {

    }

    public int getSize() {
        return studentSet.size();
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }
}