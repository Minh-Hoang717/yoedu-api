package com.yo.day1.dto.courseclass;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.domain.entity.Room;
import com.yo.day1.domain.entity.ScheduleSlots;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.domain.enums.ClassStatus;
import com.yo.day1.dto.CourseResponse;
import com.yo.day1.dto.room.RoomResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CourseClassResponse(
        Long id,
         String classCode,

         String name,
         CourseResponse course,

         RoomResponse room,

         ScheduleSlots scheduleSlot,

         Teacher mainteacher,

         Teacher assistanTeacher,
         LocalDate startDate,
         LocalDate endDate,
         int maxStudents,
         BigDecimal tuitionFee,

         ClassStatus classStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
