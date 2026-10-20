package interfaces;

import pojos.Student;

import java.util.List;

public interface SetMethods {
    void printSet();
    List<Student> sort();
    void insert(List<Student> students);
    int getSize();
}
