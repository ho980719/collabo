package com.ho.collabo.app.member.dto;

import com.ho.collabo.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String loginId;
    private String name;
    private String password;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.password = member.getPassword();
    }

}
