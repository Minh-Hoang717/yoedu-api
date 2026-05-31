package com.yo.day1.service;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.dto.CourseRequest;
import com.yo.day1.dto.CourseResponse;
import com.yo.day1.dto.room.RoomResponse;
import com.yo.day1.dto.room.RoomUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    List<Course>  findCourseIsActive();

    Optional<Course> findById(long id);
    Course save(Course course);
    // Thêm 2 method:
    CourseResponse updateCourse(long id, CourseRequest request);
    void deleteCourse(long id);

}
