package departmantManager.ProiectSCD.Employee;

import departmantManager.ProiectSCD.Department.Department;
import departmantManager.ProiectSCD.Department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeControler {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
}
