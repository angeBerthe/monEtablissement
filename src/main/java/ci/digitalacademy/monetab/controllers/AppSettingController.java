package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.AppService;
import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/appSettings")
public class AppSettingController {

    private final AppService appService;
    private final AppSettingService appSettingService;
    @GetMapping
    public String showSettingPage(Model model){
        model.addAttribute("appSetting", new AppSettingDTO());
        return "appSettings/forms";
    }


    @GetMapping("/update")
    public String showUpdateProfessorForm(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        AppSettingDTO appSettingDTO = appSettingService.findAll().stream().findFirst().orElse(null);

        model.addAttribute("setting", appSettingDTO);
        return "appSettings/forms";

    }

    @PostMapping
    public String saveSetting( AppSettingDTO appSettingDTO){
        log.debug("Request to save student");
        appSettingService.save(appSettingDTO);
        return "redirect:/";
    }
}
