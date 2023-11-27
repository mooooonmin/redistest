package com.example.redistest.domain.controller;

import com.example.redistest.domain.MemberDto;
import com.example.redistest.domain.entity.Member;
import com.example.redistest.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public List<Member> findAll() {
        List<Member> members = memberService.findAllMembers();
        log.info("Controller findAll {}", members);
        return members;
    }

    @GetMapping("/{memberId}")
    public Member findById(@PathVariable("memberId") Long id) {
        Member member = memberService.findById(id);
        log.info("Controller find {}", member);
        return member;
    }

    @PostMapping()
    public Member save(@RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.getName(), memberDto.getAge());
        Member savedMember = memberService.save(member);
        log.info("Controller save {}", savedMember);
        return savedMember;
    }

    @PutMapping("/{memberId}")
    public Member update(@PathVariable Long memberId, @RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.getName(), memberDto.getAge());
        return memberService.update(memberId, member);
    }

    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }
}
