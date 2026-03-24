package com.example.chatserver.controller;
import com.example.chatserver.domain.Member;
import com.example.chatserver.dto.MemberSaveReqDto;
import com.example.chatserver.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
@RequestMapping("/member")
public class MemberController {
    //1. 멤버 서비스가 싱글톤 객체로 만들어짐.
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //:member/create 경로로 요청
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto){
        Member member = memberService.create(memberSaveReqDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }
}
