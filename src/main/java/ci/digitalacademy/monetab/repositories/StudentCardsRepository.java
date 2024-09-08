package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.StudentCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardsRepository extends JpaRepository<StudentCards, Long> {
}
