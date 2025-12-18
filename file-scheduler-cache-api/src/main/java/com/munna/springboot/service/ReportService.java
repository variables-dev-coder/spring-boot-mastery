package com.munna.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munna.springboot.repository.FileMetadataRepository;

@Service
public class ReportService {
	
	@Autowired
    private FileMetadataRepository repository;

    public long totalFiles() {
        return repository.count();
    }

}
