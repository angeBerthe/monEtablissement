package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/schools")
public class schoolController {

    private final AppService appService;
    private final AppSettingService appSettingService;
    private final RoleUserService roleUserService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private final SchoolService schoolService;
    @GetMapping
    public String showSchoolPage(Model model){
        log.debug("Request to show school page ");
        model.addAttribute("school", new RegistrationSchoolDTO());
        return "schools/forms";
    }

    @PostMapping
    public String saveSchool(@ModelAttribute RegistrationSchoolDTO registrationSchoolDTO) throws IOException {
        log.debug("Request to save school {}", registrationSchoolDTO);
        String upload = fileStorageService.upload(registrationSchoolDTO.getFile());
        AppSettingDTO settingDTO = appSettingService.findAll().stream().findFirst().orElse(null);
        registrationSchoolDTO.setAppSetting(settingDTO);
        registrationSchoolDTO.setUrlLogo(upload);
        SchoolDTO save = schoolService.save(registrationSchoolDTO);
        createUser(save);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showUpdateProfessorForm(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        SchoolDTO schoolDTO = schoolService.findAll().stream().findFirst().orElse(null);
        RegistrationSchoolDTO registrationSchoolDto = new RegistrationSchoolDTO();
        registrationSchoolDto.setAppSetting(schoolDTO.getAppSetting());
        registrationSchoolDto.setUrlLogo(schoolDTO.getUrlLogo());
        registrationSchoolDto.setName(schoolDTO.getName());
        registrationSchoolDto.setId(schoolDTO.getId());
        model.addAttribute("schools", registrationSchoolDto);
        return "schools/forms";

    }

    public void createUser(SchoolDTO schoolDTO){

        RoleUserDTO role1 = new RoleUserDTO();
        role1.setNameRole("admin");
        RoleUserDTO role2 = new RoleUserDTO();
        role2.setNameRole("staff");

        List<RoleUserDTO> roleUsersDTO = Arrays.asList(role1, role2);
        roleUsersDTO = roleUserService.initRoles(roleUsersDTO);


        Set<RoleUserDTO> roleUserAnge = new HashSet<>();
        roleUserAnge.add(roleUsersDTO.get(0));

        Set<RoleUserDTO> roleUserStaff = new HashSet<>();
        roleUserStaff.add(roleUsersDTO.get(1));


        String passordAdmin = passwordEncoder.encode("admin");
        UserDTO ange = new UserDTO();
        ange.setPseudo("angeB");
        ange.setPassword(passordAdmin);
        ange.setCreationDate(Instant.now());
        ange.setSchool(schoolDTO);
        ange.setUserRole(roleUserAnge);

        String passordStaff = passwordEncoder.encode("staff");
        UserDTO staff = new UserDTO();
        staff.setPseudo("delmas");
        staff.setPassword(passordStaff);
        staff.setCreationDate(Instant.now());
        staff.setSchool(schoolDTO);
        staff.setUserRole(roleUserStaff);


        List<UserDTO> users = List.of(ange, staff);
        userService.initUser(users);

    }
}
