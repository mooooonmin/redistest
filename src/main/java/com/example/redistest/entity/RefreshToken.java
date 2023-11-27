package com.example.redistest.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "refresh_token") // 설정한 값을 레디스의 키값으로 프리픽스로 사용
public class RefreshToken {

    @Id // 키값, 리프레시 토큰 위치에 오토 인크리먼트
    private String authId;

    @Indexed // 값으로 검색 할 시에 추가
    private String token;

    private String role;

    @TimeToLive
    private long ttl; // 만료시간을 초단위설정

    public RefreshToken update(String token, long ttl) {
        this.token = token;
        this.ttl = ttl;
        return this;
    }

}
