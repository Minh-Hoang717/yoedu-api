package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Student;
import com.yo.day1.reponsitory.StudentReponsitory;
import com.yo.day1.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentReponsitory studentReponsitory;

    public StudentServiceImpl(StudentReponsitory studentReponsitory) {

        this.studentReponsitory = studentReponsitory;
    }

    public List<Student> findAll() {
        return studentReponsitory.findAll();
    }
}
