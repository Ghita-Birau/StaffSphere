package departmantManager.ProiectSCD.Service;

import departmantManager.ProiectSCD.DTO.EmployeeDTO;
import departmantManager.ProiectSCD.Repository.DepartmentRepository;
import departmantManager.ProiectSCD.Repository.EmployeeRepository;
import departmantManager.ProiectSCD.Entity.Employee;
import departmantManager.ProiectSCD.Request.RequestEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartmentDescription(employee.getDepartment().getDescription());
        if (employee.getManager() != null) {
            employeeDTO.setManagerName(employee.getManager().getName());
        } else {
            employeeDTO.setManagerName(null);
        }
        return employeeDTO;
    }
    public List<EmployeeDTO> getAllEmployee(){
        return employeeRepository.findAll().stream().map(this::convertEmployeeToEmployeeDTO).toList();
    }
    public List<EmployeeDTO> getEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name).stream().map(this::convertEmployeeToEmployeeDTO).toList();
    }

    public String  addEmployee(RequestEmployee requestEmployee) {
        if(employeeRepository.findEmployeeByEmailAndName(requestEmployee.getEmail(), requestEmployee.getName()) != null) {
            return "This employee is already register";
        } else {
            if(requestEmployee.getManagerName() == null) {
                if(employeeRepository.findAll().isEmpty()) {
                    Employee firstEmployee = new Employee();
                    firstEmployee.setManager(null);
                    firstEmployee.setEmail(requestEmployee.getEmail());
                    firstEmployee.setName(requestEmployee.getName());
                    if(departmentRepository.findDepartmentByDescription(requestEmployee.getDepartmentDescription()) != null) {
                        firstEmployee.setDepartment(departmentRepository.findDepartmentByDescription(requestEmployee.getDepartmentDescription()));
                        employeeRepository.save(firstEmployee);
                        return "First employee successfully added";
                    } else {
                        return "The department of this employee is not found";
                    }
                } else {
                    return "Please enter a valid manager";
                }
            } else {
                if(findManagerByName(requestEmployee.getManagerName()) != null) {
                    Employee employee = new Employee();
                    employee.setName(requestEmployee.getName());
                    employee.setEmail(requestEmployee.getEmail());
                    employee.setManager(findManagerByName(requestEmployee.getManagerName()));
                    if(departmentRepository.findDepartmentByDescription(requestEmployee.getDepartmentDescription()) != null) {
                        employee.setDepartment(departmentRepository.findDepartmentByDescription(requestEmployee.getDepartmentDescription()));
                        employeeRepository.save(employee);
                        return "Employee successfully added";
                    } else {
                        return "The department of this emplyee is not found";
                    }
                } else {
                    return "The manager of this employee is not found";
                }
            }
        }
    }
    public Employee findManagerByName(String managerName) {
        return employeeRepository.findEmployeeByManager_Name(managerName);
    }

    public String deleteEmployee(String name, String email) {
        Employee employeeToDelete = employeeRepository.findEmployeeByEmailAndName(email, name);
        if (employeeToDelete != null) {
            employeeRepository.delete(employeeToDelete);
            return "Department deleted successfully";
        } else {
            return "Department not found";
        }
    }

    public String updateEmployee(String employeeName, String employeeEmail, RequestEmployee requestEmployee) {
        if(employeeRepository.findEmployeeByEmailAndName(employeeEmail, employeeName) != null) {
            Employee updateEmployee = employeeRepository.findEmployeeByEmailAndName(employeeEmail, employeeName);
            if(requestEmployee.getName() != null) {
                updateEmployee.setName(requestEmployee.getName());
            }
            if(requestEmployee.getEmail() != null) {
                updateEmployee.setEmail(requestEmployee.getEmail());
            }
            if(requestEmployee.getDepartmentDescription() != null) {
                updateEmployee.setDepartment(departmentRepository.findDepartmentByDescription(requestEmployee.getDepartmentDescription()));
            }
            if(requestEmployee.getManagerName() != null) {
                updateEmployee.setManager(findManagerByName(requestEmployee.getManagerName()));
            }
            employeeRepository.save(updateEmployee);
            return "Successfully updated employee";
        } else {
            return "Employee not found";
        }
    }

    public List<EmployeeDTO> getAllEmployeePerDepartment(String departmentDescription) {
        return employeeRepository.findAllByDepartment_Description(departmentDescription).stream().map(this::convertEmployeeToEmployeeDTO).toList();
    }

    public List<EmployeeDTO> getAllManagersByDepartment(String departmentDescription) {
        List<Employee> employees = employeeRepository.findAllByDepartment_Description(departmentDescription);
        List<Employee> managers = new ArrayList<>();
        for(Employee employee : employees) {
            if(employee.getManager() == null) {
                managers.add(employee);
            }
        }
        return managers.stream().map(this::convertEmployeeToEmployeeDTO).toList();
    }
}
