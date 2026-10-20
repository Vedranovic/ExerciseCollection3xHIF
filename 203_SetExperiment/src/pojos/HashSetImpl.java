package pojos;

import java.util.HashSet;

public class HashSetImpl extends SetMethods {
    @Override
    public String collectSetData() {
        HashSet<Student> hashSet = new HashSet<>(getStudentSet());
        StringBuilder output = new StringBuilder();

        for (Student student : hashSet) {
            output.append(student).append("\n");
        }

        return output.toString();
    }
}
