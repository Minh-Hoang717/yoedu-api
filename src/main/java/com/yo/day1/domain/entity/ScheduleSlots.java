package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule_slots")
public class ScheduleSlots extends AuditableEntity {

    @Column(columnDefinition = "varchar(20)")
    private String slotCode;

    private byte weekday;

    private LocalTime startTime;
    private LocalTime endTime;

    @Column(columnDefinition = "varchar(255")
    private String note;

}
