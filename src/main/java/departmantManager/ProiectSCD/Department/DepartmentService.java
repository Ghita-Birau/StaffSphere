package departmantManager.ProiectSCD.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
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

    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department updateDepartment(Integer id, Map<String, Object> updates) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);

        if (existingDepartment != null) {
            // Actualizare descriere dacă este furnizată
            if (updates.containsKey("description")) {
                existingDepartment.setDescription((String) updates.get("description"));
            }

            // Verificare actualizare părinte
            if (updates.containsKey("parent")) {
                Object parentUpdate = updates.get("parent");

                if (parentUpdate instanceof Map) {
                    // Actualizare doar id-ul părintelui
                    Map<String, Object> parentUpdateMap = (Map<String, Object>) parentUpdate;
                    Integer parentId = (Integer) parentUpdateMap.get("id");
                    Department parent = new Department();
                    parent.setId(parentId);
                    existingDepartment.setParent(parent);
                } else if (parentUpdate instanceof Department) {
                    // Actualizare întreg obiectul Department al părintelui
                    Department updatedParent = (Department) parentUpdate;
                    existingDepartment.setParent(updatedParent);
                }
            }

            return departmentRepository.save(existingDepartment);
        } else {
            return null; // sau aruncă o excepție pentru a indica că departamentul nu a fost găsit
        }
    }

//    public void deleteDepartment(Integer id) {
//        Department department = departmentRepository.findById(id).orElse(null);
//        if (department != null) {
//            // Înainte de a șterge departamentul, șterge-l și pe părinte
//            Department parent = department.getParent();
//            if (parent != null) {
//                deleteDepartment(parent.getId());
//            }
//            departmentRepository.deleteById(id);
//        }
//    }
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}
