package com.yo.day1.service.impl;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.domain.entity.CourseClass;
import com.yo.day1.domain.entity.Room;
import com.yo.day1.domain.entity.ScheduleSlots;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.dto.courseclass.CourseClassResponse;
import com.yo.day1.dto.courseclass.CourseClassUpsertRequest;
import com.yo.day1.repository.CourseClassRepository;
import com.yo.day1.repository.CourseRepository;
import com.yo.day1.repository.RoomRepository;
import com.yo.day1.repository.ScheduleSlotsRepository;
import com.yo.day1.repository.TeacherRepository;
import com.yo.day1.service.CourseClassService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseClassServiceImpl implements CourseClassService {
    private final CourseClassRepository courseClassRepository;
    private final ScheduleSlotsRepository scheduleSlotsRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    private CourseClassResponse toCourseClassResponse(CourseClass courseClass) {
        return modelMapper.map(courseClass, CourseClassResponse.class);
    }
//
//    private CourseClass getCourseClass(Long id) throws NotFoundException {
//        return courseClassRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Course class not found with id: " + id));
//    }

    private void applyReferences(CourseClass cc, CourseClassUpsertRequest req) throws NotFoundException {
        Course course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + req.getCourseId()));
        cc.setCourse(course);

        Room room = roomRepository.findById(req.getRoomId())
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + req.getRoomId()));
        cc.setRoom(room);

        ScheduleSlots scheduleSlot = scheduleSlotsRepository.findById(req.getScheduleSlotId())
                .orElseThrow(() -> new NotFoundException("ScheduleSlot not found with id: " + req.getScheduleSlotId()));
        cc.setScheduleSlot(scheduleSlot);

        Teacher mainTeacher = teacherRepository.findById(req.getMainteacherId())
                .orElseThrow(() -> new NotFoundException("Main teacher not found with id: " + req.getMainteacherId()));
        cc.setMainteacher(mainTeacher);

        if (req.getAssistanTeacherId() != null) {
            Teacher assistantTeacher = teacherRepository.findById(req.getAssistanTeacherId())
                    .orElseThrow(() -> new NotFoundException("Assistant teacher not found with id: " + req.getAssistanTeacherId()));
            cc.setAssistanTeacher(assistantTeacher);
        } else {
            cc.setAssistanTeacher(null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseClassResponse> findAllCourseClasses(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            return courseClassRepository.findByNameContainingIgnoreCaseOrClassCodeContainingIgnoreCase(search, search, pageable)
                    .map(this::toCourseClassResponse);
        }
        return courseClassRepository.findAll(pageable).map(this::toCourseClassResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseClassResponse findById(Long id) throws NotFoundException {
        return toCourseClassResponse(getCourseClass(id));
    }

    @Override
    @Transactional
    public CourseClassResponse createCourseClass(CourseClassUpsertRequest req) throws NotFoundException {
        CourseClass cc = modelMapper.map(req, CourseClass.class);
        applyReferences(cc, req);
        return toCourseClassResponse(courseClassRepository.save(cc));
    }

    @Override
    @Transactional
    public CourseClassResponse updateCourseClass(Long id, CourseClassUpsertRequest req) throws NotFoundException {
        CourseClass cc = getCourseClass(id);
        modelMapper.map(req, cc);
        applyReferences(cc, req);
        return toCourseClassResponse(courseClassRepository.save(cc));
    }

    @Override
    @Transactional
    public void deleteCourseClass(Long id) throws NotFoundException {
        CourseClass cc = getCourseClass(id);
        courseClassRepository.delete(cc);
    }

    @Transactional(readOnly = true)
    public CourseClass getCourseClass(Long id) throws NotFoundException {
        return courseClassRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course class not found: " + id));
    }

}
