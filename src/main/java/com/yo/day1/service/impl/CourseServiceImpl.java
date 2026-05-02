package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.reponsitory.CourseReponsitory;
import com.yo.day1.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseReponsitory courseReponsitory;

    public List<Course> findAll(){
        return courseReponsitory.findAll();
    }

    public Optional<Course> findById(long id){
        return courseReponsitory.findById(id);
    }

    public Course save(Course course){
        return courseReponsitory.save(course);
    }
}
