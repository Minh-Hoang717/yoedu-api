package com.yo.day1.service;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.domain.entity.Student;
import com.yo.day1.dto.student.StudentResponse;
import com.yo.day1.dto.student.StudentUpsertRequest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface StudentService {
    List<StudentResponse> findAll();
    StudentResponse create(StudentUpsertRequest studentUpsertRequest);
    Optional<StudentResponse> findById(long id);
    StudentResponse update(Long id, StudentUpsertRequest studentUpsertRequest);
    void delete(Long id) throws NotFoundException;
    Student getStudentForParent(Long studentId, Long parentId) throws NotFoundException;
    Student getStudent(Long id) throws NotFoundException;
}
