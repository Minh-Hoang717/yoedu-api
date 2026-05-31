package com.yo.day1.service;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.CourseClass;
import com.yo.day1.dto.courseclass.CourseClassResponse;
import com.yo.day1.dto.courseclass.CourseClassUpsertRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseClassService {
    Page<CourseClassResponse> findAllCourseClasses(String search, Pageable pageable);
    CourseClassResponse findById(Long id) throws NotFoundException;
    CourseClassResponse createCourseClass(CourseClassUpsertRequest req) throws NotFoundException;
    CourseClassResponse updateCourseClass(Long id, CourseClassUpsertRequest req) throws NotFoundException;
    void deleteCourseClass(Long id) throws NotFoundException;
    CourseClass getCourseClass(Long id) throws NotFoundException ;
}
