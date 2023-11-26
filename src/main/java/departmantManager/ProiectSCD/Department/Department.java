package departmantManager.ProiectSCD.Department;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    Department parent;

}
