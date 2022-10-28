package com.likelion.springbootbasic.domain;

import lombok.Getter;

@Getter
public class User {

    private String id;
    private String name;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
