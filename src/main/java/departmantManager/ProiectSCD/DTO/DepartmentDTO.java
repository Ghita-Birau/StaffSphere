package departmantManager.ProiectSCD.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDTO {
    private Integer id;
    private String description;
    private String parentDescription;
}
