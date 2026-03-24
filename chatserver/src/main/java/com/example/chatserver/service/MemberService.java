package com.example.chatserver.service;


import com.example.chatserver.domain.Member;
import com.example.chatserver.dto.MemberSaveReqDto;
import com.example.chatserver.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //Dto를 받아와서 Service에서 Entity로 반환하고, 이걸 레포지토리에게 넘김.
    public Member create(MemberSaveReqDto memberSaveReqDto){
        //이미 가빕되어 있는 이메일 검증
        if(memberRepository.findByEmail(memberSaveReqDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member newMember = Member.builder()
                .name(memberSaveReqDto.getName())
                .email(memberSaveReqDto.getEmail())
                .password(memberSaveReqDto.getPassword())
                .build();
        Member member = memberRepository.save(newMember); // 이 멤버변수는 데이터베이스에 저장된 객체임.

        return member;
    }
}
