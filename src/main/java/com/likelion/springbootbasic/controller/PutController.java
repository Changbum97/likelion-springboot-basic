package com.likelion.springbootbasic.controller;

import com.likelion.springbootbasic.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // Put API
    @PutMapping("/domain")
    public String putExample() {
        return "Hello Put API";
    }

    //
    @PutMapping("/member")
    public ResponseEntity<MemberDto> putMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
