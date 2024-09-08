package ci.digitalacademy.monetab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//@DiscriminatorValue(value = "teacher")
@Entity
public class Teacher extends Person {


    @Column(name = "available")
    private boolean available;

    @Column( name = "specialty")
    private String specialty;





}
