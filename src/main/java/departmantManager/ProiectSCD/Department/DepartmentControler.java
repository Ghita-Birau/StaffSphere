package departmantManager.ProiectSCD.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentControler {

    // getAllEmployeePerDepartment
    // getAllManagersPerDepartment
    // CRUD
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/hello")
    public String HelloWord() { return "Hello Word";}

    @GetMapping("/department")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    //CRUD

    @PostMapping("/department")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    /*

    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }*/
}
