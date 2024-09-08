package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
