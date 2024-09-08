package ci.digitalacademy.monetab.repositories;


import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.Teacher;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByLastNameOrSpecialtyAndGender(String lastName, String specialty, Gender gender);
}
