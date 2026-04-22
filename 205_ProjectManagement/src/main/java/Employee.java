import java.util.List;

public class Employee {
    private int id;
    private String name;
    private int age;
    private Department department;
    private double salary;
    private List<String> skills;

    public Employee(String line) {
        String[] tokens = line.split(",");
        this.id = Integer.parseInt(tokens[0]);
        this.name = tokens[1];
        this.department = Department.valueOf(tokens[2]);
        this.age = Integer.parseInt(tokens[3]);
        this.salary = Double.parseDouble(tokens[4]);
        this.skills = List.of(tokens[5].split("\\|"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }
}
