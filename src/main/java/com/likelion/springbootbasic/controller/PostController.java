package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    // Post API
    @PostMapping("/domain")
    public String postExample() {
        return "Hello Post API";
    }

    // Request Body로 넘어온 데이터를 Map으로 받기
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();
        postData.entrySet().forEach(map -> sb.append(map.getKey() + ":" + map.getValue()).append("\n"));
        return sb.toString();
    }

    // Request Body로 넘어온 데이터를 Dto로 받기
    @PostMapping("/member-dto")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
