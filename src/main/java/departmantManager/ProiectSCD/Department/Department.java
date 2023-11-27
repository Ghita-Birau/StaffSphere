package departmantManager.ProiectSCD.Department;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String description;

    @ManyToOne
    @JoinColumn (name="parent_id")
    Department parent;

}
