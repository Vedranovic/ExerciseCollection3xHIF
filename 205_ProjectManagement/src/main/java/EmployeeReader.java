import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EmployeeReader {
    public static List<Employee> readEmployees() throws IOException {
        return Files.lines(Path.of(System.getProperty("user.dir"),
                        "src", "main", "resources", "files", "employee.csv"))
                .skip(1)
                .filter(l -> l.matches("\\d+;\\w+;\\w+\\d+;\\d+;\\w+[|]*"))
                .map(Employee::new)
                .toList();
    }
}
