package com.munna.springboot.day18.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day18.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

}
