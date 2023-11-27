package com.example.redistest.domain.service;

import com.example.redistest.domain.entity.Member;
import com.example.redistest.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + id));
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member update(Long id, Member member) {
        member.setId(id);
        return memberRepository.save(member);
    }

    public void delete(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }
}
