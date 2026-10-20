package pojos;

import java.util.TreeSet;

public class TreeSetImpl extends SetMethods {
    @Override
    public String collectSetData() {
        TreeSet<Student> treeSet = new TreeSet<>(getStudentSet());
        StringBuilder output = new StringBuilder();

        for (Student student : treeSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }
}
