package com.munna.dtodemo.service;

import com.munna.dtodemo.dto.request.UserCreateRequestDTO;
import com.munna.dtodemo.dto.response.UserResponseDTO;
import com.munna.dtodemo.entity.User;
import com.munna.dtodemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO dto) {

        // DTO → Entity
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // normally encrypt

        User savedUser = userRepository.save(user);

        // Entity → Response DTO
        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        return response;
    }
}
