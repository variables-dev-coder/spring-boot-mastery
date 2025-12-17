package com.munna.springboot.day20.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day20.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
