package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @PostMapping("/domain")
    public String postExample() {
        return "Hello Post API";
    }
}
