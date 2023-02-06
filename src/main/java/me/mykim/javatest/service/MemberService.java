package me.mykim.javatest.service;

import me.mykim.javatest.domain.Member;
import me.mykim.javatest.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
