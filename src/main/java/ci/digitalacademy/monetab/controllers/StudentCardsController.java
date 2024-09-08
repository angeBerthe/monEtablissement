package ci.digitalacademy.monetab.controllers;


import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
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
@RequestMapping("/studentCards")
public class StudentCardsController {

    private final StudentCardsService studentCardsService;
    private final StudentService studentService;

    @GetMapping("/add")
    public String showAddAbsencePage(Model model){
        model.addAttribute("studentCard", new StudentCardsDTO());
        List<StudentDTO> studentDTOS = studentService.findAll();
        model.addAttribute("students", studentDTOS);
        return "studentCards/forms";
    }

    @PostMapping
    public String saveAbsenceStudent(StudentCardsDTO studentCardsDTO){
        log.debug("Request to show save studentCards");
        studentCardsService.save(studentCardsDTO);
        return "redirect:/studentCards";
    }
    @GetMapping
    public String showStudentCardsPage(Model model){
        List<StudentCardsDTO> studentCardsDTOS = studentCardsService.findAll();
        model.addAttribute("studentCards", studentCardsDTOS);
        return "studentCards/list";
    }

    @GetMapping("/{id_cards}")
    public String showUpdateStudentCardsForms(@PathVariable Long id_cards,Model model) {
        log.debug("Request to show  update forms");
        Optional<StudentCardsDTO> studentCardsDTO = studentCardsService.findOne(id_cards);
        if (studentCardsDTO.isPresent() ) {
            model.addAttribute("studentCard", studentCardsDTO.get());
            return "studentCards/forms";
        } else {
            return "redirect:/studentCards";
        }


    }


    @PostMapping("/{id_cards}")
    public String delete(@PathVariable Long id_cards) {
        studentCardsService.delete(id_cards);
        return "redirect:/studentCards";
    }

}
