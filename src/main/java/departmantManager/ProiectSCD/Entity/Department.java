package departmantManager.ProiectSCD.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne
        @JoinColumn (name="parent_id")
        private Department parent ;
}