package com.ho.collabo.app.member.service;

import com.ho.collabo.app.member.dto.MemberDto;
import com.ho.collabo.app.member.entity.Member;
import com.ho.collabo.app.member.entity.Role;
import com.ho.collabo.app.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberDto join(MemberDto memberDto) {
        int count = memberRepository.countByLoginId(memberDto.getLoginId());
        if (count > 0)
            throw new RuntimeException("돌아가");

        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .name(memberDto.getName())
                .password(memberDto.passwordEncode(passwordEncoder))
                .role(Role.USER)
                .build();

        memberRepository.save(member);
        return new MemberDto(member);
    }

    public MemberDto login(MemberDto memberDto) {
        Member member = memberRepository.findByLoginIdAndPassword(memberDto.getLoginId(), memberDto.getPassword())
                .orElseThrow(RuntimeException::new);

        return new MemberDto(member);
    }
}
