package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dao.UserDao;
import com.likelion.springbootbasic.domain.User;
import com.likelion.springbootbasic.domain.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    // User을 Request Body로 받아 추가 후 User Return
    @PostMapping("")
    public User add(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        userDao.add(user);
        return userDao.findById(user.getId());
    }

    // User 전체 삭제
    @DeleteMapping("")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }

    // User 조회
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userDao.findById(id);
    }

    // User 전체 조회
    @GetMapping("")
    public List<User> findAll() {
        return userDao.findAll();
    }

    // 전체 User 수 조회
    @GetMapping("/count")
    public int getCount() {
        return userDao.getCount();
    }
}
