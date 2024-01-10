package departmantManager.ProiectSCD.Controller;

import departmantManager.ProiectSCD.DTO.DepartmentDTO;
import departmantManager.ProiectSCD.Service.DepartmentService;
import departmantManager.ProiectSCD.Request.RequestDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")

public class DepartmentControler {

    // getAllManagersPerDepartment
    private final DepartmentService departmentService;

    @GetMapping("/all/departments")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/create/department")
    public String createDepartment(@RequestBody RequestDepartment requestCreateDepartment) {
        return departmentService.addDepartment(requestCreateDepartment);
    }

    @GetMapping("/department/by/{description}")
    public DepartmentDTO getDepartment(@PathVariable String description) {
        return departmentService.getDepartmentByDescription(description);
    }

    @PutMapping("/update/department/{description}")
    public String updateDepartment(@PathVariable String description, @RequestBody RequestDepartment requestUpdateDepartment) {
        return departmentService.updateDepartment(description, requestUpdateDepartment);
    }

    @DeleteMapping("/department/by/{description}")
    public void deleteDepartment(@PathVariable String description) {
        departmentService.deleteDepartmentRecursive(description);
    }
}
