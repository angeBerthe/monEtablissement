package ci.digitalacademy.monetab.services.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentPdfDTO {



    private LocalDate birthday;


    private String matricule;


    private String phoneNumberFather;


    private String gender;


    private String phoneNumber;


    private String firstName;


    private String lastName;
}
