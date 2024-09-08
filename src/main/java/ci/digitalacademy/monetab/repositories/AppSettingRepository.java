package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {

    List<AppSetting> findBySmtpUsername(String username);
}
