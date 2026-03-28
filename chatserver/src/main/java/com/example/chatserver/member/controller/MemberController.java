package com.example.chatserver.member.controller;
import com.example.chatserver.common.auth.JwtTokenProvider;
import com.example.chatserver.member.domain.Member;
import com.example.chatserver.member.dto.MemberLoginReqDto;
import com.example.chatserver.member.dto.MemberSaveReqDto;
import com.example.chatserver.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// Controller
@RestController
@RequestMapping("/member")
public class MemberController {
    //1. 멤버 서비스가 싱글톤 객체로 만들어짐.
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    //의존성 주입
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //member/create 경로로 post 요청
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto){
        Member member = memberService.create(memberSaveReqDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginReqDto memberLoginReqDto){
        //1. email , password 검증
        Member member = memberService.login(memberLoginReqDto);

        //2. 일치할 경우 access token 발행
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> loginInfo = new HashMap<>();
        //로그인 응답 정보는 다음과같다.
        loginInfo.put("id",member.getId());
        loginInfo.put("token", jwtToken);
        //객체 형태로 리턴해주면(Map) json 형태로 나감.
        return new ResponseEntity<>(loginInfo,HttpStatus.OK);
    }
}
