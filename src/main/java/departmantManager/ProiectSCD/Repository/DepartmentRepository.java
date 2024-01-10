package departmantManager.ProiectSCD.Repository;

import departmantManager.ProiectSCD.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Integer> {
    Department findDepartmentByDescription(String description);

    List<Department> findAllByParent(Department parent);
}
