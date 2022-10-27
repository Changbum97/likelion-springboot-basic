package com.likelion.springbootbasic.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {

    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    // Path Variable 실습
    @GetMapping("/path-variable/{v1}/{v2}")
    public String getPathVariable(@PathVariable int v1, @PathVariable int v2) {
        return "Path Variable : " + (v1 + v2);
    }

    // Query Parameter 실습
    @GetMapping("/query-param")
    public String getQueryParam(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {
        return "Name : " + name + "\nEmail : " + email + "\nOranization : " + organization;
    }

    // Path Variable, Query Parameter 같이 써보기
    @GetMapping("/both-param/{v1}")
    public String getBothParam(@PathVariable int v1, @RequestParam int v2) {
        return "Path Variable : " + v1 + "\nQuery Param : " + v2;
    }

}
