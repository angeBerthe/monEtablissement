package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.Absence;
import lombok.*;

import java.util.Set;


@Getter
@Setter
public class StudentDTO extends PersonDTO{


    private String matricule;

    private String phoneNumberTutor;

    private Set<AbsenceDTO> absences;
}
