package com.example.userversioning.controller.v2;

import com.example.userversioning.dto.v2.UserV2Response;
import com.example.userversioning.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/users")
public class UserV2Controller {

    private final UserService userService;

    public UserV2Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserV2Response> usersV2() {
        return userService.getUsers()
                .stream()
                .map(user -> new UserV2Response(
                        user.getId(),
                        user.getName(),
                        "USER"   // simulated new field in V2
                ))
                .collect(Collectors.toList());
    }
}
