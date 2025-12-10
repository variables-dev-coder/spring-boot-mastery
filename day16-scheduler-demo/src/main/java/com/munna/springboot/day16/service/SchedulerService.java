package com.munna.springboot.day16.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.munna.springboot.day16.entity.TimeLog;
import com.munna.springboot.day16.repository.TimeLogRepository;



@Service
public class SchedulerService {
	
	@Autowired
    private TimeLogRepository repo;

    // Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void fixedRateTask() {
        TimeLog log = new TimeLog();
        log.setTimestamp(LocalDateTime.now());
        repo.save(log);
        System.out.println("FixedRate → Saved at: " + LocalDateTime.now());
    }

    // Runs 3 seconds after previous execution completes
    @Scheduled(fixedDelay = 3000)
    public void fixedDelayTask() {
        System.out.println("FixedDelay → Running at: " + LocalDateTime.now());
    }

    // Runs every 10 seconds
    @Scheduled(cron = "*/10 * * * * *")
    public void cronTask() {
        System.out.println("Cron → Running every 10 seconds: " + LocalDateTime.now());
    }

}
