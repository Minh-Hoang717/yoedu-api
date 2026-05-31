package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.dto.CourseRequest;
import com.yo.day1.dto.CourseResponse;
import com.yo.day1.service.CourseService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getCourse() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findAll()));
    }

    //                                                  xem khi naof dung long khi naof Long
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathParam("id") Long id) {
        Optional<Course> course= courseService.findById(id);
        if(course.isPresent()){
            return ResponseEntity.ok(ApiResponse.success(course.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(Course course){
        return ResponseEntity.ok(ApiResponse.success(courseService.save(course)));
    }

//    @PostMapping
//    public ResponseEntity<ApiResponse<CourseResponse>> create(@Valid @RequestBody CourseRequest request) {
//        CourseResponse saved = courseService.createCourse(request); // bạn cần thêm method này trong service
//        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Course created", saved));
//    }

    //
    // controllers/CourseController.java
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable long id,
            @Valid @RequestBody CourseRequest request) {
        CourseResponse updated = courseService.updateCourse(id, request);
        return ResponseEntity.ok(ApiResponse.success("Course updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Course deleted"));
    }

}
