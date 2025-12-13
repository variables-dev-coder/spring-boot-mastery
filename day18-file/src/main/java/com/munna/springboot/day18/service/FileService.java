package com.munna.springboot.day18.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.munna.springboot.day18.entity.FileData;
import com.munna.springboot.day18.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FileService {
	
	 @Value("${file.upload-dir}")
	    private String uploadDir;

	    private final FileDataRepository repo;

	    // UPLOAD METHOD
	    public String uploadFile(MultipartFile file) throws IOException {

	        String filePath = uploadDir + file.getOriginalFilename();

	        File saveFile = new File(filePath);
	        saveFile.getParentFile().mkdirs();  // create folder

	        file.transferTo(saveFile); // save file physically

	        FileData data = new FileData(
	                null,
	                file.getOriginalFilename(),
	                file.getContentType(),
	                file.getSize(),
	                filePath
	        );

	        repo.save(data);

	        return "File uploaded successfully: " + file.getOriginalFilename();
	    }

	    // DOWNLOAD METHOD
	    public byte[] downloadFile(Long id) throws IOException {
	        FileData data = repo.findById(id)
	                .orElseThrow(() -> new RuntimeException("File not found"));

	        return Files.readAllBytes(new File(data.getFilePath()).toPath());
	    }

}
