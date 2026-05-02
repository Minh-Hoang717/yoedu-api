package com.yo.day1.reponsitory;

import com.yo.day1.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReponsitory extends JpaRepository<Student, Long> {


}
