package departmantManager.ProiectSCD.Department;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue

    Integer id;

    String Description;

    @ManyToOne
    Department parent;


}
