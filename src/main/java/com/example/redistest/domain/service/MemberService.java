package com.example.redistest.domain.service;

import com.example.redistest.domain.entity.Member;
import com.example.redistest.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, Member> redisTemplate;

    public List<Member> findAllMembers() {
        // 레디스 캐시에서 데이터 조회
        List<Member> cachedMembers = redisTemplate.opsForList().range("members", 0, -1);
        if (cachedMembers != null && !cachedMembers.isEmpty()) {
            return cachedMembers;
        }

        // 데이터베이스에서 데이터 조회
        List<Member> members = memberRepository.findAll();

        // 조회 결과를 레디스에 저장
        redisTemplate.opsForList().rightPushAll("members", members);

        return members;
    }

    public Member findById(Long id) {
        // 레디스 캐시에서 데이터 조회
        Member cachedMember = redisTemplate.opsForValue().get("member_" + id);
        if (cachedMember != null) {
            return cachedMember;
        }

        // 데이터베이스에서 데이터 조회
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + id));

        // 조회 결과를 레디스에 저장
        redisTemplate.opsForValue().set("member_" + id, member);

        return member;
    }

    public Member save(Member member) {
        Member savedMember = memberRepository.save(member);

        // 저장된 데이터를 레디스에 추가
        redisTemplate.opsForValue().set("member_" + savedMember.getId(), savedMember);

        // 캐시된 목록 업데이트
        redisTemplate.opsForList().rightPush("members", savedMember);

        return savedMember;
    }

    public Member update(Long id, Member member) {
        member.setId(id);
        Member updatedMember = memberRepository.save(member);

        // 업데이트된 데이터를 레디스에 업데이트
        redisTemplate.opsForValue().set("member_" + updatedMember.getId(), updatedMember);

        return updatedMember;
    }

    public void delete(Long id) {
        // 회원을 찾지 못하면 삭제 작업을 수행하지 않음
        Member member = findById(id);
        if (member == null) {
            return;
        }

        memberRepository.deleteById(id);

        // 레디스에서 데이터 삭제
        redisTemplate.delete("member_" + id);

        // 캐시된 목록 업데이트
        redisTemplate.opsForList().remove("members", 0, member);
    }

}
