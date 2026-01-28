package com.munna.dtodemo.service;

import com.munna.dtodemo.dto.request.UserCreateRequestDTO;
import com.munna.dtodemo.dto.response.UserResponseDTO;


public interface UserService {
	
    UserResponseDTO createUser(UserCreateRequestDTO dto);
    
}
