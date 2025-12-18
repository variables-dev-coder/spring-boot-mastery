package com.munna.springboot.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.munna.springboot.entity.FileMetadata;
import com.munna.springboot.repository.FileMetadataRepository;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private FileMetadataRepository repository;

    public FileMetadata uploadFile(MultipartFile file) throws IOException {

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setFileType(file.getContentType());
        metadata.setSize(file.getSize());
        metadata.setUploadTime(LocalDateTime.now());

        return repository.save(metadata);
    }

    @Cacheable(value = "files", key = "#id")
    public FileMetadata getFileById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
}

