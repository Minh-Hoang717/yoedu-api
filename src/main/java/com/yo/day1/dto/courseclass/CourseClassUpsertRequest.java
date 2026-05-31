package com.yo.day1.dto.courseclass;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.domain.entity.Room;
import com.yo.day1.domain.entity.ScheduleSlots;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.domain.enums.ClassStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CourseClassUpsertRequest{
    @NotBlank
    @Size(max = 20)
    String classCode;

    String name;

    Long courseId;


    Long roomId;


    Long scheduleSlotId;
    Long mainteacherId;

    Long assistanTeacherId;

    @NotNull
    LocalDate startDate;
    LocalDate endDate;

    Integer maxStudents;

    @NotNull
    @DecimalMin("0.0")
    BigDecimal tuitionFee;

    @NotNull
    ClassStatus classStatus;

}
        

