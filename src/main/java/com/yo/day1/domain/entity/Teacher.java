package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

// domain/entity/Teacher.java
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "teachers")
public class Teacher extends AuditableEntity {
    @Column(unique = true, nullable = false)
    private String teacherCode;
    private String fullName;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private TeacherRole teacherRole = TeacherRole.TEACHER;
    private String cccdImageUrl;
    private boolean isActive = true;

    public enum TeacherRole { TEACHER, ASSISTANT, BOTH }
}
