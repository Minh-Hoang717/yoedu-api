package com.yo.day1.dto.student;

import com.yo.day1.domain.entity.Parent;
import com.yo.day1.domain.enums.Gender;
import com.yo.day1.domain.enums.StudentStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpsertRequest {


    @Size(min = 2, max = 10)
    private String studentCode;

    @NotBlank(message = "Name is required")
    private String fullName;


    private LocalDate dateOfBirth;



    private Gender gender = Gender.OTHER;


    private String gradeLevel;


    private String schoolName;

    @Pattern(regexp = "^(84|0[35789])+([0-9]{8})$")
    @NotBlank
    private String phone;


    private String description;


    private Long parentId;


    private StudentStatus status = StudentStatus.ACTIVE;

    @Min(value = 1)
    @Max(value = 10)
    private BigDecimal latestScore = BigDecimal.ZERO;



    private String note;

}
