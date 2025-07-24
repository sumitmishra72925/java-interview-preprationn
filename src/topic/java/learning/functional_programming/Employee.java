package topic.java.learning.functional_programming;

//Immutable class
public final class Employee {
    private final String name;
    private final String department;
    private final int age;
    private final double salary;

    public Employee(String name, String department, int age, double salary) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "{" +
                "  name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
