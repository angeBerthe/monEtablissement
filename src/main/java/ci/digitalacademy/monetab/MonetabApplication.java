package ci.digitalacademy.monetab;

import ci.digitalacademy.monetab.models.*;
import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

@SpringBootApplication
@AllArgsConstructor
public class MonetabApplication implements CommandLineRunner {

    private final BCryptPasswordEncoder passwordEncoder;
    private UserService userService;
    private StudentService studentService;

    private TeacherService teacherService;

    private AddressService addressService;
    private RoleUserService roleUserService;
    private AppSettingService appSettingService;
    private SchoolService schoolService;

    public static void main(String[] args) {
        SpringApplication.run(MonetabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
       RoleUserDTO role1 = new RoleUserDTO();
        role1.setRole("admin");
        RoleUserDTO role2 = new RoleUserDTO();
        role2.setRole("staff");
        RoleUserDTO role3 = new RoleUserDTO();
        role3.setRole("other");

        List<RoleUserDTO> roleUsersDTO = Arrays.asList(role1, role2, role3);
       roleUsersDTO = roleUserService.initRoles(roleUsersDTO);

        AppSettingDTO appSettingDTO = new AppSettingDTO();

        appSettingDTO.setSmtpServer("mail");
        appSettingDTO.setSmtpUsername("monEcole");
        appSettingDTO.setSmtpPassword("monEcole123");
        appSettingDTO.setSmtpPort(587);

        AppSettingDTO settingDTO = appSettingService.initApp(appSettingDTO);

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setName("upb");
        schoolDTO.setUrlLogo("https://cdn-icons-png.freepik.com/256/8074/8074788.png?semt=ais_hybrid");
        schoolDTO.setAppSetting(settingDTO);
        schoolDTO = schoolService.initSchool(schoolDTO);

        Set<RoleUserDTO> roleUserAnge = new HashSet<>();
        roleUserAnge.add(roleUsersDTO.get(0));

        Set<RoleUserDTO> roleUserStaff = new HashSet<>();
        roleUserStaff.add(roleUsersDTO.get(1));

        Set<RoleUserDTO> roleUserOther = new HashSet<>();
        roleUserOther.add(roleUsersDTO.get(2));

        UserDTO ange = new UserDTO();
        ange.setPseudo("angeB");
        ange.setPassword("angeB123");
        ange.setCreationDate(Instant.now());
        ange.setSchool(schoolDTO);
        ange.setRoles(roleUserAnge);

        UserDTO staff = new UserDTO();
        staff.setPseudo("delmas");
        staff.setPassword("delmas007");
        staff.setCreationDate(Instant.now());
        staff.setSchool(schoolDTO);
        staff.setRoles(roleUserStaff);

        UserDTO other = new UserDTO();
        other.setPseudo("bakus");
        other.setPassword("bakus005");
        other.setCreationDate(Instant.now());
        other.setSchool(schoolDTO);
        other.setRoles(roleUserOther);

        List<UserDTO> users = List.of(ange, staff, other);
        userService.initUser(users);

*/

    }

}
