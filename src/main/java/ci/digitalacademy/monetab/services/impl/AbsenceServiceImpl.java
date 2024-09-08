package ci.digitalacademy.monetab.services.impl;

import ci.digitalacademy.monetab.models.Absence;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.repositories.AbsenceRepository;
import ci.digitalacademy.monetab.repositories.StudentRepository;
import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.mapper.AbsenceMapper;
import ci.digitalacademy.monetab.services.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Permet de créer les constructeurs des propriétés requit
@Slf4j
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Request to save: {}", absenceDTO);
      /*  if (absenceDTO.getStudent() != null && absenceDTO.getStudent().getId_person() != null) {
            StudentDTO studentDTO = studentService.findOne(absenceDTO.getStudent().getId_person())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));
            Student student = studentMapper.toEntity(studentDTO);

            student = studentRepository.save(student);
            absenceDTO.setStudent(student);
        }*/
        Absence absence = absenceMapper.toEntity(absenceDTO);
        absence = absenceRepository.save(absence);
        return absenceMapper.fromEntity(absence);
    }
    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO) {
        log.debug("Request to update: {}", absenceDTO);
        return findOne(absenceDTO.getId_absence()).map(existingAbsence ->{
            existingAbsence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
            return save(absenceDTO);
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id_absence) {
        log.debug("Resquest to get id: {}", id_absence);
        return absenceRepository.findById(id_absence).map(absence -> {
            return absenceMapper.fromEntity(absence);
        });
    }

    @Override
    public List<AbsenceDTO> findAll() {
        return absenceRepository.findAll().stream().map(absence -> {
            return absenceMapper.fromEntity(absence);
        }).toList();
    }

    @Override
    public void delete(Long id_absence) {
        log.debug("Request to delete {}", id_absence);
        absenceRepository.deleteById(id_absence);
    }
}
