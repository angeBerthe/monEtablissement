package ci.digitalacademy.monetab.services.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class StudentExcelDTO {

    @ExcelProperty("Date de Naissance")
    private LocalDate birthday;

    @ExcelProperty("Matricule")
    private String matricule;

    @ExcelProperty("Numéro de Téléphone du Père")
    private String phoneNumberFather;

    @ExcelProperty("Genre")
    private String gender;

    @ExcelProperty("Numéro de Téléphone")
    private String phoneNumber;

    @ExcelProperty("Prénom")
    private String firstName;

    @ExcelProperty("Nom")
    private String lastName;

}
