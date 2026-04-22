public class ProjectMember {
    private String projectId;
    private int amount;
    private String role;
    private int allocationRole;

    public ProjectMember(String line) {
        String[] tokens = line.split(",");
        this.projectId = tokens[0];
        this.amount = Integer.parseInt(tokens[1]);
        this.role = tokens[2];
        this.allocationRole = Integer.parseInt(tokens[3]);
    }

    public String getProjectId() {
        return projectId;
    }

    public int getAmount() {
        return amount;
    }

    public String getRole() {
        return role;
    }

    public int getAllocationRole() {
        return allocationRole;
    }
}
