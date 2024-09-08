package ci.digitalacademy.monetab.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "appSetting")
public class AppSetting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smtp_server")
    private String smtpServer;

    @Column(name = "smtp_port")
    private int smtpPort;

    @Column(name = "smtp_username")
    private String smtpUsername;

    @Column(name = "smtp_password")
    private String smtpPassword;

    @OneToOne(mappedBy = "appSetting", cascade = CascadeType.ALL)
    private School school;
}
