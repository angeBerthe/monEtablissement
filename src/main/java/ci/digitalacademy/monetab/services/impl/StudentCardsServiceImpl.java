package ci.digitalacademy.monetab.services.impl;


import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.StudentCards;
import ci.digitalacademy.monetab.repositories.StudentCardsRepository;
import ci.digitalacademy.monetab.repositories.StudentRepository;
import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.mapper.StudentCardsMapper;
import ci.digitalacademy.monetab.services.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Permet de créer les constructeurs des propriétés requit
@Slf4j
public class StudentCardsServiceImpl implements StudentCardsService {

    private final StudentCardsRepository studentCardsRepository;
    private final StudentCardsMapper studentCardsMapper;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    @Override
    public StudentCardsDTO save(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to save: {}", studentCardsDTO);
      /*  if (studentCardsDTO.getStudent() != null && studentCardsDTO.getStudent().getId_person() != null) {
            StudentDTO studentDTO = studentService.findOne(studentCardsDTO.getStudent().getId_person())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));
            Student student = studentMapper.toEntity(studentDTO);
            student = studentRepository.save(student);
            studentCardsDTO.setStudent(student);
        }*/
        StudentCards studentCards = studentCardsMapper.toEntity(studentCardsDTO);
        studentCards = studentCardsRepository.save(studentCards);
        return studentCardsMapper.fromEntity(studentCards);
    }



    @Override
    public StudentCardsDTO update(StudentCardsDTO studentCardsDTO) {
        return findOne(studentCardsDTO.getId_cards()).map(existingStudentCards ->{
            existingStudentCards.setExpirationDate(studentCardsDTO.getExpirationDate());
            return save(studentCardsDTO);
        }).orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public Optional<StudentCardsDTO> findOne(Long id_cards) {
        log.debug("Resquest to get id: {}", id_cards);
        return studentCardsRepository.findById(id_cards).map(studentStudentCards -> {
            return studentCardsMapper.fromEntity(studentStudentCards);
        });
    }

    @Override
    public List<StudentCardsDTO> findAll() {
        return studentCardsRepository.findAll().stream().map(studentStudentCards -> {
            return studentCardsMapper.fromEntity(studentStudentCards);
        }).toList();
    }

    @Override
    public void delete(Long id_cards) {
        studentCardsRepository.deleteById(id_cards);
    }
}
