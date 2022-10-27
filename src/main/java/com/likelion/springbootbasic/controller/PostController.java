package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @PostMapping("/domain")
    public String postExample() {
        return "Hello Post API";
    }

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();
        postData.entrySet().forEach(map -> sb.append(map.getKey() + ":" + map.getValue()).append("\n"));
        return sb.toString();
    }

    @PostMapping("/member-dto")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
