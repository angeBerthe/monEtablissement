package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final RoleUserService roleUserService;

    @GetMapping("/add")
    public String showAddUserPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "users/forms";
    }

    @PostMapping
    public String saveUser(UserDTO userDTO){
        log.debug("Request to show save user");
        if (userDTO.getCreationDate() == null) {
            userDTO.setCreationDate(Instant.now());
        }
        userService.save(userDTO);
        return "redirect:/users";
    }
    @GetMapping
    public String showUserPage(Model model){
        List<UserDTO> userDTOS = userService.findAll();
        model.addAttribute("users", userDTOS);
        return "users/list";
    }

    @GetMapping("/{id_user}")
    public String showUpdateTeacherForms(@PathVariable Long id_user, Model model){
        log.debug("Request to show update user forms");
        Optional<UserDTO> userDTO = userService.findOne(id_user);
        if (userDTO.isPresent()){
            model.addAttribute("user", userDTO.get());
            return "users/forms";
        }else {
            return "redirect:/users";
        }
    }

    @PostMapping("/{id_user}")
    public String deleteUser(@PathVariable Long id_user) {
        userService.delete(id_user);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String searchTeachers(@RequestParam LocalDate date  , @RequestParam String role, Model model)
    {
        List<UserDTO> users = userService.findByCreatedDateLessThanAndRoleUserNameRole(Instant.from(date.atStartOfDay(ZoneOffset.systemDefault())), role);
        model.addAttribute("users", users);
        model.addAttribute("date", date);
        model.addAttribute("role", role);
        model.addAttribute("roles", roleUserService.findAll());

        return "users/list";
    }
}
