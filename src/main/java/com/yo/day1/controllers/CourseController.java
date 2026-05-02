package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.service.CourseService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
