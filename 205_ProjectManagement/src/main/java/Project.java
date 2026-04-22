public class Project {
    private String projectId;
    private String name;
    private Department department;
    private double budget;

    public Project(String line) {
        String[] tokens = line.split(",");
        this.projectId = tokens[0];
        this.name = tokens[1];
        this.department = Department.valueOf(tokens[2]);
        this.budget = Double.parseDouble(tokens[3]);
    }

    public String getName() {
        return name;
    }
}
