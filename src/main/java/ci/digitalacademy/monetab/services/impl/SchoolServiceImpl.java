package ci.digitalacademy.monetab.services.impl;


import ci.digitalacademy.monetab.models.School;
import ci.digitalacademy.monetab.repositories.SchoolRepository;
import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.mapper.SchoolMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Permet de créer les constructeurs des propriétés requit
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        log.debug("Request to save: {}", schoolDTO );
        School school = schoolMapper.toEntity(schoolDTO);
        school = schoolRepository.save(school);
        return schoolMapper.fromEntity(school);
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) {
        return findOne(schoolDTO.getId()).map(existingSchool ->{
            existingSchool.setName(schoolDTO.getName());
            existingSchool.setUrlLogo(schoolDTO.getUrlLogo());
            return save(schoolDTO);
        }).orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public Optional<SchoolDTO> findOne(Long id) {
        log.debug("Resquest to get id: {}", id);
        return schoolRepository.findById(id).map(school -> {
            return schoolMapper.fromEntity(school);
        });
    }

    @Override
    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll().stream().map(school ->{
            return schoolMapper.fromEntity(school);
        }).toList();
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        log.debug("Request to init school {}", schoolDTO);
        SchoolDTO school = existingSchool();
        if (Objects.isNull(school)){
           return save(schoolDTO);
        }
        return school;
    }

    @Override
    public SchoolDTO existingSchool() {
        log.debug("Request to check existing school");
        List<SchoolDTO> schoolDTO = findAll();
        return schoolDTO.stream().findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}
