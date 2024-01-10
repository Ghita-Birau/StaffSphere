package departmantManager.ProiectSCD.Repository;

import departmantManager.ProiectSCD.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Integer>{
    List<Employee> findEmployeeByName(String name);
    Employee findEmployeeByManager_Name(String name);
    Employee findEmployeeByEmailAndName(String email, String name);
    List<Employee> findAllByDepartment_Description(String description);
}