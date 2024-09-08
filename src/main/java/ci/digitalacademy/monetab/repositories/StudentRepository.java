package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //    etape 1)
    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(String lastName, String matricule , Gender gender);
}
