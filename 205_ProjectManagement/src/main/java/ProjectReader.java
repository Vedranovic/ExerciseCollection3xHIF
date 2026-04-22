import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProjectReader {
    public static List<Project> readProjects() throws IOException {
        return Files.lines(Path.of(System.getProperty("user.dir"),
                        "src", "main", "resources", "files", "projects.csv"))
                .skip(1)
                .filter(l -> l.matches("P\\d+,\\w+,\\w+,\\d+"))
                .map(Project::new)
                .toList();
    }
}
