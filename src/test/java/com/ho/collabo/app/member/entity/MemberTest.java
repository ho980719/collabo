package com.ho.collabo.app.member.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        Member member = Member.builder()
                .name("김준호")
                .build();

        Assertions.assertThat(member.getName()).isEqualTo("김준호");
    }
}