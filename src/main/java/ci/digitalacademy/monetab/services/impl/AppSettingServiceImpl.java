package ci.digitalacademy.monetab.services.impl;

import ci.digitalacademy.monetab.models.AppSetting;
import ci.digitalacademy.monetab.repositories.AppSettingRepository;
import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.mapper.AppSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Permet de créer les constructeurs des propriétés requit
@Slf4j
public class AppSettingServiceImpl implements AppSettingService {

    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;
    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        log.debug("Request to save: {}", appSettingDTO );
        AppSetting appSetting = appSettingMapper.toEntity(appSettingDTO);
        appSetting = appSettingRepository.save(appSetting);
        return appSettingMapper.fromEntity(appSetting);
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        return findOne(appSettingDTO.getId()).map(existingAppSetting ->{
            existingAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
            existingAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
            existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            existingAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
            return save(appSettingDTO);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<AppSettingDTO> findOne(Long id) {
        log.debug("Resquest to get id: {}", id);
        return appSettingRepository.findById(id).map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        });
    }

    @Override
    public List<AppSettingDTO> findAll() {
        return appSettingRepository.findAll().stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }

    @Override
    public void delete(Long id) {

        appSettingRepository.deleteById(id);

    }

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        log.debug("Request to init app {}", appSettingDTO);
        AppSettingDTO settingDTO = existingParameter();
       if (settingDTO == null){
           return save(appSettingDTO);
       }
        return settingDTO;
    }
    @Override
    public AppSettingDTO existingParameter() {
        log.debug("Request to check existing Parameter");
        List<AppSettingDTO> appSettingDTO = findAll();
        //récuperation du 1er enregistrement
        return appSettingDTO.stream().findFirst().orElse(null);
    }

    @Override
    public List<AppSettingDTO> findAllBySmtpUsername(String smtpUsername) {
        return appSettingRepository.findBySmtpUsername(smtpUsername).stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }
}
