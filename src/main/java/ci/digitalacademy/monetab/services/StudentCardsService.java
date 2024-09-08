package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;

import java.util.List;
import java.util.Optional;

public interface StudentCardsService {

    StudentCardsDTO save(StudentCardsDTO studentCardsDTO);

    StudentCardsDTO update(StudentCardsDTO studentCardsDTO);

    Optional<StudentCardsDTO> findOne(Long id_cards);

    List<StudentCardsDTO> findAll();

    void delete(Long id_cards);
}
