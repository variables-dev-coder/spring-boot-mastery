package com.munna.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
