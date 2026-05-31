package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import com.yo.day1.domain.enums.ClassStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_classes")
public class CourseClass extends AuditableEntity {

    @NotNull
    private String classCode;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id") //,  nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "room_id") //,  nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "schedule_slot_id") //,  nullable = false)
    private ScheduleSlots scheduleSlot;

    @ManyToOne
    @JoinColumn(name = "main_teacher_id" ) //, nullable = false)
    private Teacher mainteacher;

    @ManyToOne
    @JoinColumn(name = "assistant_teacher_id")
    private Teacher assistanTeacher;

    private LocalDate startDate;
    private LocalDate endDate;

    private int maxStudents;

    @Column(columnDefinition = "decimal", precision = 12, scale = 2)
    private BigDecimal tuitionFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClassStatus status = ClassStatus.OPEN;

}
