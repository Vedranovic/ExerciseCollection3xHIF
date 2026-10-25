package pojos;

import java.util.*;

public abstract class SetMethods {
    private Set<Student> studentSet;

    public SetMethods() {
        this.studentSet = Collections.emptySet();
    }

    public abstract String collectSetData();

    public abstract List<Student> sort();

    public abstract void insert(List<Student> students);

    public abstract int getSize();

    public Set<Student> getStudentSet() {
        return studentSet;
    }
}