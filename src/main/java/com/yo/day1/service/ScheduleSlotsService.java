package com.yo.day1.service;

import com.yo.day1.domain.entity.ScheduleSlots;

import java.util.List;
import java.util.Optional;

public interface ScheduleSlotsService {
    List<ScheduleSlots> findAll();
    Optional<ScheduleSlots> findById(long id);
    ScheduleSlots save(ScheduleSlots scheduleSlots);
    void delete(ScheduleSlots scheduleSlots);
}
