package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/absences")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final StudentService studentService;

    @GetMapping("/add")
    public String showAddAbsencePage(Model model){
        model.addAttribute("absence", new AbsenceDTO());
        List<StudentDTO> studentsDtos = studentService.findAll();
        System.out.println(studentsDtos);
        model.addAttribute("students", studentsDtos);
        return "absences/forms";
    }

    @PostMapping
    public String saveAbsenceStudent(AbsenceDTO absenceDTO){
        log.debug("Request to show save absence");
        absenceService.save(absenceDTO);
        return "redirect:/absences";
    }
    @GetMapping
    public String showAbsencePage(Model model){
        List<AbsenceDTO> absenceDTOS = absenceService.findAll();
        model.addAttribute("absences", absenceDTOS);
        return "absences/list";
    }

    @GetMapping("/{id_absence}")
    public String showUpdateAbsenceForms(@PathVariable Long id_absence,  Model model) {
        log.debug("Request to show update forms");
        Optional<AbsenceDTO> absenceDTO = absenceService.findOne(id_absence);
        if (absenceDTO.isPresent() ) {
            model.addAttribute("absence", absenceDTO.get());
            return "absences/forms";
        } else {
            return "redirect:/absences";
        }


    }


    @PostMapping("/{id_absence}")
    public String delete(@PathVariable Long id_absence) {
        absenceService.delete(id_absence);
        return "redirect:/absences";
    }
}
