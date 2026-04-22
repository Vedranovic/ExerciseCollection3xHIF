import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProjectMemberReader {
    public static List<ProjectMember> readProjectMembers() throws IOException {
        return Files.lines(Path.of(System.getProperty("user.dir"),
                        "src", "main", "resources", "files", "project_members.csv"))
                .skip(1)
                .filter(l -> l.matches("P\\d+,\\d+,[\\w ]+,\\d+"))
                .map(ProjectMember::new)
                .toList();
    }
}
