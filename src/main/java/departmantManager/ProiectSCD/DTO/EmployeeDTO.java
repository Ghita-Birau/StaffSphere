package departmantManager.ProiectSCD.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
    Integer id;
    String email;
    String name;
    String departmentDescription;
    String managerName;
}
