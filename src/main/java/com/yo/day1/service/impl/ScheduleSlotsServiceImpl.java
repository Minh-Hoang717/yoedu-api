package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.ScheduleSlots;
import com.yo.day1.repository.ScheduleSlotsRepository;
import com.yo.day1.service.ScheduleSlotsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleSlotsServiceImpl implements ScheduleSlotsService {
    private final ScheduleSlotsRepository scheduleSlotsRepository;

    public List<ScheduleSlots> findAll() {
        return scheduleSlotsRepository.findAll();
    }
    public Optional<ScheduleSlots>  findById(long id) {
        return scheduleSlotsRepository.findById(id);
    }
    public ScheduleSlots save(ScheduleSlots scheduleSlots) {
        return scheduleSlotsRepository.save(scheduleSlots);
    }
    public void delete(ScheduleSlots scheduleSlots) {
        scheduleSlotsRepository.delete(scheduleSlots);
    }
}
