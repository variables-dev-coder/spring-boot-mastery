package com.munna.dtodemo.repository;

import com.munna.dtodemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	
}
