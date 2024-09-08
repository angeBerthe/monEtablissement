package ci.digitalacademy.monetab.services.impl;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.repositories.StudentRepository;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save:{}",studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.fromEntity(student);

    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        log.debug("Request to update:{}", studentDTO);
        return findOne(studentDTO.getId_person()).map(existiengStudent->{
            existiengStudent.setLastName(studentDTO.getLastName());
            existiengStudent.setFirstName(studentDTO.getFirstName());
            existiengStudent.setBirthDate(studentDTO.getBirthDate());
            existiengStudent.setPhoneNumberTutor(studentDTO.getPhoneNumberTutor());
            existiengStudent.setUrlPicture(studentDTO.getUrlPicture());
            existiengStudent.setPhoneNumber(studentDTO.getPhoneNumber());
            existiengStudent.setGender(studentDTO.getGender());
            existiengStudent.setMatricule(studentDTO.getMatricule());
            return save(studentDTO);
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public Optional<StudentDTO> findOne(Long id_person) {
        log.debug("Resquest to get id: {}", id_person);
        return studentRepository.findById(id_person).map(student -> {
            return studentMapper.fromEntity(student);
        });
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            return studentMapper.fromEntity(student);
        }).toList();
    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, String gender) {

        List<Student> students = studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query  , query , Gender.valueOf(gender));
        return students.stream().map(student -> studentMapper.fromEntity(student)).toList();
    }


    @Override
    public void delete(Long id_person) {
        studentRepository.deleteById(id_person);
    }
}
