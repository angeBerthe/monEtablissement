package ci.digitalacademy.monetab.services.impl;

import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final RoleUserService roleUserService;
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;
    private final UserService userService;

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return appSettingService.initApp(appSettingDTO);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        //AppSettingDTO settingDTO = appSettingService.initApp(appSettingDTO);
       // schoolDTO.setAppSetting(settingDTO);
        schoolDTO = schoolService.initSchool(schoolDTO);
        return schoolService.initSchool(schoolDTO);
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO) {
        return roleUserService.initRoles(roleUserDTO);
    }

    @Override
    public void initUsers(List<UserDTO> userDTOS, List<RoleUserDTO> roleUserDTO, SchoolDTO schoolDTO) {

        for (int i = 0; i < userDTOS.size(); i++) {
            UserDTO user = userDTOS.get(i);
            user.setCreationDate(Instant.now());
            user.setSchool(schoolDTO);

            Set<RoleUserDTO> roles = new HashSet<>();
            user.setUserRole(roles);
        }

        userService.initUser(userDTOS);
    }
}
