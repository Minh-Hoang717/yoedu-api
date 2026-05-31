package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.dto.CourseRequest;
import com.yo.day1.dto.CourseResponse;
import com.yo.day1.repository.CourseRepository;
import com.yo.day1.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Optional<Course> findById(long id){
        return courseRepository.findById(id);
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public List<Course>  findCourseIsActive(){
        return courseRepository.findCourseIsActive();
    }
    // them updata vs request
    @Override
    public CourseResponse updateCourse(long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        course.setCourseCode(request.courseCode());
        course.setName(request.name());
        course.setDescription(request.description());
        course.setTuitionFee(request.tuitionFee());
        course.setTotalSessions(request.totalSession());
        course.setIsActive(request.isActive());
        Course saved = courseRepository.save(course);
        return mapToResponse(saved);
    }

    @Override
    public void deleteCourse(long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCourseCode(),
                course.getName(),
                course.getDescription(),
                course.getTuitionFee(),
                course.getTotalSessions(),
                course.getIsActive()
        );
    }
}
