package departmantManager.ProiectSCD.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

//    public Department getDepartmentById(Integer id) {
//        return departmentRepository.findById(id).orElse(null);
//    }

    /*public Department updateDepartment(Integer id, Department updatedDepartment) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);

        if (existingDepartment != null) {
            existingDepartment.setDescription(updatedDepartment.getDescription());
            existingDepartment.setParentId(updatedDepartment.getParentId());
            return departmentRepository.save(existingDepartment);
        } else {
            return null; // sau aruncă o excepție pentru a indica că departamentul nu a fost găsit
        }
    }*/

//    public void deleteDepartment(Integer id) {
//        departmentRepository.deleteById(id);
//    }
}
