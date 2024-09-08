package ci.digitalacademy.monetab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//@DiscriminatorValue(value = "student")
@Entity
public class Student extends Person {

    @Column( name = "matricule", unique = true)
    private String matricule;

    @Column(name = "phone_number_tutor", unique = true)
    private String phoneNumberTutor;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Absence> absences;

}
