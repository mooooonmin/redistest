package com.example.redistest.domain.repository;

import com.example.redistest.domain.entity.Member;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Cacheable(value = "allMembers", key = "'allMembers'")
    List<Member> findAll();

    @Cacheable(value = "members", key = "'member_' + #id", unless = "#result == null")
    Optional<Member> findById(Long id);

    @Override
    @CacheEvict(value = "allMembers", key = "'allMembers'")
    <S extends Member> S save(S entity);

    @CacheEvict(value = "members", key = "'member_' + #entity.id")
    void delete(Member entity);

    @CacheEvict(value = "members", key = "'member_' + #entity.id")
    void deleteAll(Iterable<? extends Member> entities);
}