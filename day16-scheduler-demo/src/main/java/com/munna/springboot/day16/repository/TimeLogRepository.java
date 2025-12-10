package com.munna.springboot.day16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.springboot.day16.entity.TimeLog;



public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {

}
