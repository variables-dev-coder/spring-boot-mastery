package com.munna.springboot.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.munna.springboot.entity.FileMetadata;
import com.munna.springboot.service.FileService;
import com.munna.springboot.service.ReportService;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getFile(@PathVariable Long id) { 
    	
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> totalFiles() {
        return ResponseEntity.ok(reportService.totalFiles());
    }
}