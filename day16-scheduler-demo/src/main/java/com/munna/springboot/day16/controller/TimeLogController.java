package com.munna.springboot.day16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.munna.springboot.day16.entity.TimeLog;
import com.munna.springboot.day16.repository.TimeLogRepository;


@RestController
public class TimeLogController {
	
	@Autowired
    private TimeLogRepository repo;

    @GetMapping("/logs")
    public List<TimeLog> getAllLogs() {
        return repo.findAll();
    }

}
