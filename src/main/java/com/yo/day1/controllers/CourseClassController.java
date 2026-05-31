package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.dto.courseclass.CourseClassResponse;
import com.yo.day1.dto.courseclass.CourseClassUpsertRequest;
import com.yo.day1.service.CourseClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/course-classes")
@RequiredArgsConstructor
public class CourseClassController {

    private final CourseClassService courseClassService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseClassResponse>>> getAllCourseClasses(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(courseClassService.findAllCourseClasses(search, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseClassResponse>> getCourseClassById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(ApiResponse.success(courseClassService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseClassResponse>> createCourseClass(@Valid @RequestBody CourseClassUpsertRequest request) throws NotFoundException {
        CourseClassResponse response = courseClassService.createCourseClass(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Course class created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseClassResponse>> updateCourseClass(@PathVariable Long id, @Valid @RequestBody CourseClassUpsertRequest request) throws NotFoundException {
        CourseClassResponse response = courseClassService.updateCourseClass(id, request);
        return ResponseEntity.ok(ApiResponse.success("Course class updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourseClass(@PathVariable Long id) throws NotFoundException {
        courseClassService.deleteCourseClass(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Course class deleted successfully"));
    }
}
