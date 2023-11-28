package departmantManager.ProiectSCD.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DepartmentControler {

    // getAllEmployeePerDepartment
    // getAllManagersPerDepartment
    // CRUD
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/department")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return departmentService.updateDepartment(id, updates);
    }

    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }
}
