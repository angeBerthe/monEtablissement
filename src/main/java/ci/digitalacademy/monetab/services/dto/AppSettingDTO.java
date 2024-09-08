package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.School;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSettingDTO {

    private Long id;

    private String smtpServer;

    private int smtpPort;

    private String smtpUsername;

    private String smtpPassword;

    //private SchoolDTO school;
}
