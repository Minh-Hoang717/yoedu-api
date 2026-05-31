package com.yo.day1.repository;

import com.yo.day1.domain.entity.ScheduleSlots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleSlotsRepository extends JpaRepository<ScheduleSlots, Long> {
}
