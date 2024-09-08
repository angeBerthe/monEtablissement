package ci.digitalacademy.monetab.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentCards")
public class StudentCards implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cards;

    @Column(name = "reference")
    private String reference;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "issue_date")
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
