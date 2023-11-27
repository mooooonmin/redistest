package com.example.redistest.repository;

import com.example.redistest.domain.entity.Member;
import com.example.redistest.domain.entity.Members;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@CacheConfig(cacheNames = "members") // 캐시 어노테이션 적용
public class MemberRepository {

    private final Map<Long, Member> store = new LinkedHashMap<>();

    @Cacheable(key = "'all'")
    public Members findAll() {
        List<Member> members = store.values().stream().toList();
        log.info("Repository findAll {}", members);
        return new Members(members);
    }

    @Cacheable(key = "#memberId", unless = "#result == null")
    public Member findById(Long memberId) {
        Member member = store.get(memberId);
        log.info("Repository find {}", member);
        return member;
    }

    @CachePut(key = "#memberId")
    @CacheEvict(key = "'all'")
    public Member save(Member member) {
        Long newId = calculateId();
        member.setId(newId);

        log.info("Repository save {}", member);

        store.put(member.getId(), member);
        return member;
    }

    private Long calculateId() {
        if (store.isEmpty()) {
            return 1L;
        }

        int lastIndex = store.size() - 1;
        return (Long) store.keySet().toArray()[lastIndex] + 1;
    }

    @CachePut(key = "#member.id")
    @CacheEvict(key = "'all'")
    public Member update(Member member) {
        log.info("Repository update {}", member);
        store.put(member.getId(), member);
        return member;
    }

    @Caching(evict = {
            @CacheEvict(key = "'all'"),
            @CacheEvict(key = "#member.id")
    })
    public void delete(Member member) {
        log.info("Repository delete {}", member);
        store.remove(member.getId());
    }
}

