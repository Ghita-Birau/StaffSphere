package departmantManager.ProiectSCD.Employee;

import departmantManager.ProiectSCD.Department.Department;
import departmantManager.ProiectSCD.Department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        // Verificați dacă managerul există deja în baza de date
        if (employee.getManager() != null && employee.getManager().getId() != null) {
            Employee existingManager = employeeRepository.findById(employee.getManager().getId()).orElse(null);

            // Dacă managerul nu există, aruncăm o excepție sau gestionăm eroarea în consecință
            if (existingManager == null) {
                throw new IllegalArgumentException("Managerul nu există în baza de date.");
            }

            // Setăm managerul pe employee
            employee.setManager(existingManager);
        }

        // Verificați dacă departamentul există deja în baza de date
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department existingDepartment = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);

            // Dacă departamentul nu există, aruncăm o excepție sau gestionăm eroarea în consecință
            if (existingDepartment == null) {
                throw new IllegalArgumentException("Departamentul nu există în baza de date.");
            }

            // Setăm departamentul pe employee
            employee.setDepartment(existingDepartment);
        }

        return employeeRepository.save(employee);
    }
//    public Employee addEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
