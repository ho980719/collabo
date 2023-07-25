package com.ho.collabo.app.member.repository;

import com.ho.collabo.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    int countByLoginId(String loginId);

    Optional<Member> findByLoginIdAndPassword(String loginId, String password);
}
