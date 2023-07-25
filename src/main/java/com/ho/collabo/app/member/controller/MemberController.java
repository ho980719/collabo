package com.ho.collabo.app.member.controller;

import com.ho.collabo.app.member.dto.MemberDto;
import com.ho.collabo.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members/join")
    public ResponseEntity join(@RequestBody MemberDto memberDto) {
        return new ResponseEntity(memberService.join(memberDto), HttpStatus.OK);
    }

    @PostMapping("/api/v1/members/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto) {
        memberService.login(memberDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
