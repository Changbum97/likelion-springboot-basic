package com.likelion.springbootbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {

    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/path-variable/{v1}/{v2}")
    public String getPathVariable(@PathVariable int v1, @PathVariable int v2) {
        return "Path Variable : " + (v1 + v2);
    }
}
