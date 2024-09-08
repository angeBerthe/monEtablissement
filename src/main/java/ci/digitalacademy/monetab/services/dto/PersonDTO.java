package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.Address;
import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.User;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id_person;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String urlPicture;

    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;

    private Address address;

    private User user;

}
