package com.munna.springboot.scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.munna.springboot.repository.FileMetadataRepository;

@Component
public class FileCleanupScheduler {
	
	@Autowired
    private FileMetadataRepository repository;

    @Scheduled(cron = "*/30 * * * * ?") // Runs every 30 second
    public void cleanupOldFiles() {

        LocalDateTime limit = LocalDateTime.now().minusDays(7);

        repository.findAll().stream()
                .filter(file -> file.getUploadTime().isBefore(limit))
                .forEach(repository::delete);

        System.out.println("Scheduled Cleanup Completed");
    }

}
