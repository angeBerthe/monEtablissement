package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequiredArgsConstructor
public class GetNameSchoolController {

    private final SchoolService schoolService;

    @ModelAttribute
    public void getNameSchoolController(Model model){
        model.addAttribute("schoolName", schoolService.findAll().stream().findFirst().orElse(null));
    }
}
