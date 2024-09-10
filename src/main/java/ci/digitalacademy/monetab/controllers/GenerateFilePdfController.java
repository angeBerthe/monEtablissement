package ci.digitalacademy.monetab.controllers;

import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.DocumentFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class GenerateFilePdfController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/students")
    public String exportStudentsToPdf() throws IOException, DocumentFormatException {
        List<StudentDTO> studentDtos = studentService.findAll();

        // Définir le chemin de sauvegarde pour le fichier PDF
        String folderPath = "C:\\Users\\HP\\Documents\\Formation_ATOS\\monetab\\src\\main\\resources\\FilePDF";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Nom du fichier PDF
        String fileName = "students.pdf";
        File file = new File(folderPath + File.separator + fileName);

        // Créer un document PDF et l'enregistrer dans le répertoire local
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            // Titre du document
            document.add(new Paragraph("Liste des étudiants"));

            // Ajouter les informations des étudiants
            for (StudentDTO student : studentDtos) {
                document.add(new Paragraph("Nom : " + student.getFirstName() + " " + student.getLastName()));
                document.add(new Paragraph("Matricule : " + student.getMatricule()));
                document.add(new Paragraph("Téléphone : " + student.getPhoneNumber()));
                document.add(new Paragraph("Téléphone du tuteur : " + student.getPhoneNumberTutor()));
                document.add(new Paragraph("Genre : " + student.getGender().name()));
                document.add(new Paragraph("Date de naissance : " + student.getBirthDate().toString()));
                document.add(new Paragraph("\n"));  // Saut de ligne entre les étudiants
            }

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/reports"; // Redirection après la génération du PDF
    }

    @GetMapping("/teachers")
    public String exportTeachersToPdf() throws IOException, DocumentFormatException, DocumentException {
        List<TeacherDTO> teacherDtos = teacherService.findAll();

        // Définir le chemin de sauvegarde pour le fichier PDF
        String folderPath = "C:\\Users\\HP\\Documents\\Formation_ATOS\\monetab\\src\\main\\resources\\FilePDF";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Nom du fichier PDF
        String fileName = "teachers.pdf";
        File file = new File(folderPath + File.separator + fileName);

        // Créer un document PDF et l'enregistrer dans le répertoire local
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            // Titre du document
            document.add(new Paragraph("Liste des enseignants"));

            // Ajouter les informations des enseignants
            for (TeacherDTO teacher : teacherDtos) {
                document.add(new Paragraph("Nom : " + teacher.getFirstName() + " " + teacher.getLastName()));
                document.add(new Paragraph("Spécialité : " + teacher.getSpecialty()));
                document.add(new Paragraph("Téléphone : " + teacher.getPhoneNumber()));
                document.add(new Paragraph("Disponibilité : " + (teacher.isAvailable() ? "Disponible" : "Non disponible")));
                document.add(new Paragraph("Genre : " + teacher.getGender().name()));
                document.add(new Paragraph("Date de naissance : " + teacher.getBirthDate().toString()));
                document.add(new Paragraph("\n"));  // Saut de ligne entre les enseignants
            }

            document.close();
        }

        return "redirect:/reports"; // Redirection après la génération du PDF
    }
}