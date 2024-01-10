package departmantManager.ProiectSCD.Service;

import departmantManager.ProiectSCD.DTO.DepartmentDTO;
import departmantManager.ProiectSCD.Entity.Employee;
import departmantManager.ProiectSCD.Repository.DepartmentRepository;
import departmantManager.ProiectSCD.Repository.EmployeeRepository;
import departmantManager.ProiectSCD.Request.RequestDepartment;
import departmantManager.ProiectSCD.Entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor

public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentDTO convertDepartmentToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setDescription(department.getDescription());
        if (department.getParent() != null) {
            departmentDTO.setParentDescription(department.getParent().getDescription());
        } else {
            departmentDTO.setParentDescription(null);
        }
        return departmentDTO;
    }
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::convertDepartmentToDepartmentDTO).toList();
    }
    public String addDepartment(RequestDepartment requestCreateDepartment) {
        if(requestCreateDepartment.getParentDescription() == null) {
            if(departmentRepository.findAll().isEmpty()) {
                Department firstDepartment = new Department();
                firstDepartment.setParent(null);
                firstDepartment.setDescription(requestCreateDepartment.getDescription());
                departmentRepository.save(firstDepartment);
                return "First department successfully added";
            } else {
                return "Please enter a valid parent";
            }
        } else {
            if(findParentByDescription(requestCreateDepartment.getParentDescription()) != null) {
                Department newDepartment = new Department();
                newDepartment.setParent(findParentByDescription(requestCreateDepartment.getParentDescription()));
                newDepartment.setDescription(requestCreateDepartment.getDescription());
                departmentRepository.save(newDepartment);
                return "Department added successfully";
            }
            else {
                return "Department parent not found";
            }
        }
    }

    public DepartmentDTO getDepartmentByDescription(String description) {
        return convertDepartmentToDepartmentDTO(departmentRepository.findDepartmentByDescription(description));
    }

    public String updateDepartment(String description, RequestDepartment requestDepartment) {
        if(departmentRepository.findDepartmentByDescription(description) != null) {
            Department updateDepartment = departmentRepository.findDepartmentByDescription(description);
            if(requestDepartment.getDescription() != null) {
                updateDepartment.setDescription(requestDepartment.getDescription());
            }
            if(requestDepartment.getParentDescription() != null) {
                updateDepartment.setParent(findParentByDescription(requestDepartment.getParentDescription()));
            }
            departmentRepository.save(updateDepartment);
            return "Successfully updated department";
        } else {
            return "Department not found";
        }
    }

    public String deleteDepartmentRecursive(String description) {
        Department departmentToDelete = departmentRepository.findDepartmentByDescription(description);
        if (departmentToDelete != null) {
            deleteDepartmentAndChildren(departmentToDelete);
            return "Department and its children deleted successfully";
        } else {
            return "Department not found";
        }
    }

    private void deleteDepartmentAndChildren(Department department) {
        List<Department> children = departmentRepository.findAllByParent(department);
        for (Department child : children) {
            deleteDepartmentAndChildren(child);
        }

        List<Employee> employees = employeeRepository.findAllByDepartment_Description(department.getDescription());
        employeeRepository.deleteAll(employees);

        departmentRepository.delete(department);
    }

    public Department findParentByDescription(String description) {
        return departmentRepository.findDepartmentByDescription(description);
    }
}
