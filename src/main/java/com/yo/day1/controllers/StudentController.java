package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.dto.student.StudentResponse;
import com.yo.day1.dto.student.StudentUpsertRequest;
import com.yo.day1.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentController {
    private final StudentService studentService;
//    @GetMapping
//    public ResponseEntity<String> home() {
//        return ResponseEntity.ok("\"data\":\"This is my content\"");
//    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> studentReponsitory() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentUpsertRequest req){
        return ResponseEntity.ok(studentService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> create(@PathVariable Long id, StudentUpsertRequest req){
        return ResponseEntity.ok(studentService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse> delete(@PathVariable Long id) throws NotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
