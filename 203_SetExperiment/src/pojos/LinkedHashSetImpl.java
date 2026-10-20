package pojos;

import java.util.LinkedHashSet;

public class LinkedHashSetImpl extends SetMethods {
    @Override
    public String collectSetData() {
        LinkedHashSet<Student> linkedHashSet = new LinkedHashSet<>(getStudentSet());
        StringBuilder output = new StringBuilder();

        for (Student student : linkedHashSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }
}
