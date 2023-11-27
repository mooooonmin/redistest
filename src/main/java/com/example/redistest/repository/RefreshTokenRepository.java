package com.example.redistest.repository;

import com.example.redistest.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    // 레디스 레포지토리 사용방식

    // JpaRepository 사용하는 것처럼 -> CrudRepository 인터페이스 상속
    // @Id 또는 @Indexed 어노테이션을 적용한 프로퍼티들만 CrudRepository가 제공하는 findBy~ 구문 사용 가능
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByAuthId(String authId);

}
