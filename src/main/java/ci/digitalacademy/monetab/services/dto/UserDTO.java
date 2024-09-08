package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.RoleUser;
import ci.digitalacademy.monetab.models.School;
import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id_user;

    private String pseudo;

    private String password;

    private Instant creationDate;

    private Set<RoleUserDTO> userRole;

    private SchoolDTO school;

}
