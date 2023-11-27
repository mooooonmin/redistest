package com.example.redistest.controller;

import com.example.redistest.domain.MemberDto;
import com.example.redistest.domain.entity.Member;
import com.example.redistest.domain.entity.Members;
import com.example.redistest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public Members findAll() {
        Members members = memberRepository.findAll();
        log.info("Controller findAll {}", members);
        return members;
    }

    @GetMapping("/members/{memberId}")
    public Member findById(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId);
        log.info("Controller find {}", member);
        return member;
    }

    @PostMapping("/members")
    public Member save(@RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.getName(), memberDto.getAge());
        Member savedMember = memberRepository.save(member);
        log.info("Controller save {}", savedMember);
        return savedMember;
    }

    @PutMapping("/members/{memberId}")
    public Member update(@PathVariable Long memberId, @RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.getName(), memberDto.getAge());
        member.setId(memberId);
        return memberRepository.update(member);
    }

    @DeleteMapping("/members/{memberId}")
    public void delete(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId);
        log.info("Controller delete {}", member);
        memberRepository.delete(member);
    }
}
