package ci.digitalacademy.monetab.models;



import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "pseudo",unique = true)
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private Instant creationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleUser> roleUser;

    @ManyToOne
    private School school;
}
