package com.ho.collabo.app.member.dto;

import com.ho.collabo.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    // 비밀번호 암호화 메소드
    public String passwordEncode(PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(this.password);
    }
}
