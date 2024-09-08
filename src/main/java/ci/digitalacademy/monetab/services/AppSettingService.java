package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {

    AppSettingDTO save(AppSettingDTO appSettingDTO);

    AppSettingDTO update(AppSettingDTO appSettingDTO);

    Optional<AppSettingDTO> findOne(Long id);

    List<AppSettingDTO> findAll();

    void delete(Long id);

    //initialisation de l'objet
    AppSettingDTO initApp(AppSettingDTO appSettingDTO);

    //Vérification de l'existance de parametrage et retour de l'objet de l'objet appSetting
    AppSettingDTO existingParameter();

    List<AppSettingDTO> findAllBySmtpUsername(String smtpUsername);
}
