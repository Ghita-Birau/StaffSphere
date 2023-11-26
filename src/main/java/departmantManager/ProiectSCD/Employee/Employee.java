package departmantManager.ProiectSCD.Employee;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @Id
            @GeneratedValue
    Integer id;
    String name;
    Integer departmentId;
    Integer managerId;
    String email;
}
