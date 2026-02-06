package com.example.userversioning.dto.v1;

public class UserV1Response {

    private Long id;
    private String name;

    public UserV1Response(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
