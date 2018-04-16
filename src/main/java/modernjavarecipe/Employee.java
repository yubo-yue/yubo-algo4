package modernjavarecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Employee {
    private static final Employee DEFAULT_EMPLOYEE = new Employee(0, "UNKNOWN");
    private int id;
    private String name;

    public static void main(String[] args) {
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "Seth Curry"),
                new Employee(2, "Kevin Durant"),
                new Employee(3, "Draymod Green"),
                new Employee(4, "Klay Thompson")
        );

        Employee maxId = employees.stream()
                .max(Comparator.comparingInt(Employee::getId)).orElse(DEFAULT_EMPLOYEE);

        Employee maxName = employees.stream()
                .max(Comparator.comparing(Object::toString)).orElse(DEFAULT_EMPLOYEE);

        System.out.println(maxId);
        System.out.println(maxName);
    }
}
