package com.example.chatserver.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Getter, Setter, toString 매서드가 내부적으로 구현되게됨.
//DTO에서는 setter를 사용해도 상관없다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    private String name;
    private String email;
    private String password;
}
