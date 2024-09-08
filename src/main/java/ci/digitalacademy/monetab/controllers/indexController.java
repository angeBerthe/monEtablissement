package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class indexController {

    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    @GetMapping
    public String initialiserApp(){
        List<AppSettingDTO> appSettingDTO = appSettingService.findAll();
        List<SchoolDTO> schoolDTO = schoolService.findAll();

        if (appSettingDTO.isEmpty()){
            return "redirect:/appSettings";
        } else if (schoolDTO.isEmpty()) {
            return "redirect:/schools";
        }
        return "redirect:/auth";
    }



}
