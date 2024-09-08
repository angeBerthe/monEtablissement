package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AbsenceDTO {

    private Long id_absence;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date absenceDate;

    private int absenceNumber;

    private StudentDTO student;
}
