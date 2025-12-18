package com.munna.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.entity.FileMetadata;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

}
