package ci.digitalacademy.monetab.controllers;


import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.StudentExcelDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.TeacherExcelDTO;
import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excels")
@RequiredArgsConstructor
public class GeneretedFileExcelController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("/students")
    public String exportStudentToExcel() throws IOException {
        List<StudentDTO> studentDtos = studentService.findAll();

        List<StudentExcelDTO> studentExcelDTOS = studentDtos.stream().map(student -> {
            StudentExcelDTO dto = StudentExcelDTO.builder()
                    .phoneNumberFather(student.getPhoneNumberTutor())
                    .phoneNumber(student.getPhoneNumber())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .birthday(student.getBirthDate())
                    .gender(student.getGender().name())
                    .matricule(student.getMatricule())
                    .build();
            return dto;
        }).toList();

        // Chemin du dossier de sauvegarde
        String folderPath = "C:\\Users\\HP\\Documents\\Formation_ATOS\\monetab\\src\\main\\resources\\FileExcel";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Nom du fichier Excel
        String fileName = "students.xlsx";
        File file = new File(folderPath + File.separator + fileName);

        // Écrire le fichier Excel dans le dossier avec EasyExcel
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            EasyExcel.write(fileOutputStream, StudentExcelDTO.class)
                    .sheet("Students")
                    .doWrite(studentExcelDTOS);
        } catch (Exception e) {
            e.printStackTrace();  // Affiche la pile d'appels complète
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }

        // Ajuster automatiquement la largeur des colonnes avec Apache POI
        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            Sheet sheet = workbook.getSheet("Students");

            // Ajuster automatiquement la largeur de chaque colonne
            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();  // Affiche la pile d'appels complète
            throw new RuntimeException("Failed to adjust column widths: " + e.getMessage(), e);
        }

        return "redirect:/reports";
    }

    @PostMapping("/teachers")
    public String exportTeacherToExcel() throws IOException {
        List<TeacherDTO> teacherDTOS = teacherService.findAll();

        List<TeacherExcelDTO> teacherExcelDTOS = teacherDTOS.stream().map(teacherDTO -> {
            String availabilityStatus = teacherDTO.isAvailable() ? "Disponible" : "Non disponible";
            TeacherExcelDTO dto = TeacherExcelDTO.builder()
                    .available(availabilityStatus)
                    .phoneNumber(teacherDTO.getPhoneNumber())
                    .firstName(teacherDTO.getFirstName())
                    .lastName(teacherDTO.getLastName())
                    .birthday(teacherDTO.getBirthDate())
                    .gender(teacherDTO.getGender().name())
                    .specialty(teacherDTO.getSpecialty())
                    .build();
            return dto;
        }).toList();

        // Chemin du dossier de sauvegarde
        String folderPath = "C:\\Users\\HP\\Documents\\Formation_ATOS\\monetab\\src\\main\\resources\\FileExcel";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Nom du fichier Excel
        String fileName = "teachers.xlsx";
        File file = new File(folderPath + File.separator + fileName);

        // Écrire le fichier Excel dans le dossier avec EasyExcel
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            EasyExcel.write(fileOutputStream, TeacherExcelDTO.class)
                    .sheet("Teachers")
                    .doWrite(teacherExcelDTOS);
        } catch (Exception e) {
            e.printStackTrace();  // Affiche la pile d'appels complète
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }

        // Ajuster automatiquement la largeur des colonnes avec Apache POI
        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            Sheet sheet = workbook.getSheet("Teachers");

            // Ajuster automatiquement la largeur de chaque colonne
            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();  // Affiche la pile d'appels complète
            throw new RuntimeException("Failed to adjust column widths: " + e.getMessage(), e);
        }

        return "redirect:/reports";
    }

}