package com.likelion.springbootbasic.domain.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String id;
    private String name;
    private String password;

    public UserRequestDto(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
