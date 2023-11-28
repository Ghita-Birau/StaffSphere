package departmantManager.ProiectSCD.Employee;

import departmantManager.ProiectSCD.Department.Department;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    String email;
}
