package ci.digitalacademy.monetab.services.dto;


import ci.digitalacademy.monetab.models.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class StudentCardsDTO {

    private Long id_cards;

    private String reference;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date expirationDate;

    private StudentDTO student;

}
