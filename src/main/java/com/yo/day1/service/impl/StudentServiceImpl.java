package com.yo.day1.service.impl;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.Student;
import com.yo.day1.dto.student.StudentResponse;
import com.yo.day1.dto.student.StudentUpsertRequest;
import com.yo.day1.repository.ParentRepository;
import com.yo.day1.repository.StudentRepository;
import com.yo.day1.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;

//    public StudentServiceImpl(StudentReponsitory studentReponsitory) {
//
//        this.studentReponsitory = studentReponsitory;
//    }


    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream().map(student -> map(student)).toList();
    }




    private StudentResponse map(Student student) {
        return modelMapper.map(student, StudentResponse.class);
    }

    public Optional<StudentResponse> findById(long id) {
        return studentRepository.findById(id).map(student -> map(student));
    }


//    public StudentResponse create(StudentUpsertRequest studentUpsertRequest) {
//        Student student = modelMapper.map(studentUpsertRequest, Student.class);
//        parentRepository.findById(studentUpsertRequest.getParentId())
//                .ifPresent(parent -> student.setParent(parent));
//        student.setCreatedAt(LocalDateTime.now());
//        student.setUpdatedAt(LocalDateTime.now());
//        Student result = studentReponsitory.save(student);
//        return map(result);
//    }

    public StudentResponse create(StudentUpsertRequest studentUpsertRequest) {
        Student student = modelMapper.map(studentUpsertRequest, Student.class);

        // Chỉ tìm parent nếu parentId có giá trị
        if (studentUpsertRequest.getParentId() != null) {
            parentRepository.findById(studentUpsertRequest.getParentId())
                    .ifPresent(student::setParent);
        }

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student result = studentRepository.save(student);
        return map(result);
    }
    public StudentResponse update(Long id, StudentUpsertRequest studentUpsertRequest) {
        Student student = modelMapper.map(studentUpsertRequest, Student.class);
        student.setId(id);
        parentRepository.findById(studentUpsertRequest.getParentId())
                .ifPresent(parent -> student.setParent(parent));
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student result = studentRepository.save(student);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else {
            throw new NoSuchElementException("Student with id " + id + " does not exist");
        }
    }
    @Transactional(readOnly = true)
    public Student getStudentForParent(Long studentId, Long parentId) throws NotFoundException {
        Student student = getStudent(studentId);
        if (student.getParent() == null || !student.getParent().getId().equals(parentId)) {
            throw new org.springframework.security.access.AccessDeniedException("Student does not belong to current parent account");
        }
        return student;
    }

    public Student getStudent(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

//    private StudentResponse map(Student student) {
//        StudentResponse studentResponse = new StudentResponse();
//
//
//        ParentResponse parentResponse = new ParentResponse();
//        if(student.getParent() != null) {
//            parentResponse.setId(student.getParent().getId());
//            parentResponse.setFullName(student.getParent().getFullName());
//            parentResponse.setPhone(student.getParent().getPhone());
//            parentResponse.setGender(student.getParent().getGender());
//            parentResponse.setAddress(student.getParent().getAddress());
//            parentResponse.setEmail(student.getParent().getEmail());
//            parentResponse.setPhone(student.getParent().getPhone());
//            parentResponse.setAddress(student.getParent().getAddress());
//            ............
//        }
//    }
}
