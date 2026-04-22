import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private List<Employee> employees;
    private List<Project> projects;
    private List<ProjectMember> projectMembers;
    private Map<Integer, Employee> empById;
    private Map<String, Project> projectById;

    public Main() throws IOException {
        this.employees = EmployeeReader.readEmployees();
        this.projects = ProjectReader.readProjects();
        this.projectMembers = ProjectMemberReader.readProjectMembers();
        this.empById = new HashMap<>();
        this.projectById = new HashMap<>();
    }

    public static void main(String[] args) {

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<ProjectMember> getProjectMembers() {
        return projectMembers;
    }

    public Map<Integer, Employee> getEmpById() {
        return empById;
    }

    public Map<String, Project> getProjectById() {
        return projectById;
    }
}
