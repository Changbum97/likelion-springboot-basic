package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        log.info("hello로 요청이 들어왔습니다.");
        return "Hello World";
    }

    // Path Variable 실습
    @GetMapping("/path-variable/{v1}/{v2}")
    public String getPathVariable(@PathVariable int v1, @PathVariable int v2) {
        log.info("path-variable : {}, {}", v1, v2);
        return "Path Variable : " + (v1 + v2);
    }

    // Query Parameter 실습
    @GetMapping("/query-param")
    public String getQueryParam(@RequestParam String name, @RequestParam int age) {
        return "Name : " + name + "\nAge : " + age;
    }

    // Path Variable, Query Parameter 같이 써보기
    @GetMapping("/both-param/{name}")
    public String getBothParam(@PathVariable String name, @RequestParam int age) {
        return "Name : " + name + "\nAge : " + age;
    }

    // Map으로 Request Param 받기
    @GetMapping("/request-map")
    public String getRequestMap(@RequestParam Map<String, Object> param) {
        param.entrySet().forEach((p)-> {
            System.out.printf("Key : %s,  Value : %s\n", p.getKey(), p.getValue());
        });
        return "request-map 호출 완료";
    }

    // DTO를 통해 Query Parameter 받기
    @GetMapping("/request-dto")
    public String getRequestDto(MemberDto memberDto) {
        return memberDto.toString();
    }
}
