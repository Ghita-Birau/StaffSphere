package departmantManager.ProiectSCD.Controller;

import departmantManager.ProiectSCD.DTO.EmployeeDTO;
import departmantManager.ProiectSCD.Request.RequestEmployee;
import departmantManager.ProiectSCD.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")

public class EmployeeControler {

    private final EmployeeService employeeService;

    @GetMapping("/all/employee")
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/by/{name}")
    public List<EmployeeDTO> getEmployee(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @PostMapping("/create/employee")
    public String createEmployee(@RequestBody RequestEmployee requestEmployee) {
        return employeeService.addEmployee(requestEmployee);
    }
    @PutMapping("/update/employee/{name}/{email}")
    public String updateDepartment(@PathVariable String name, @PathVariable String email, @RequestBody RequestEmployee requestEmployee) {
        return employeeService.updateEmployee(name, email, requestEmployee);
    }

    @DeleteMapping("/employee/by/{name}/{email}")
    public void deleteEmployee(@PathVariable String name, @PathVariable String email) {
        employeeService.deleteEmployee(name, email);
    }

    @GetMapping("/all/employee/department/{departmentName}")
    public List<EmployeeDTO> getAllEmployeeByDepartment(@PathVariable String departmentName) {
        return employeeService.getAllEmployeePerDepartment(departmentName);
    }

    @GetMapping("/all/managers/department/{departmentName}")
    public List<EmployeeDTO> getAllManagersByDepartment(@PathVariable String departmentName) {
        return employeeService.getAllManagersByDepartment(departmentName);
    }
}
