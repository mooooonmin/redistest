/*
package com.example.redistest.service.impl;

import com.example.redistest.dto.TestDto;
import com.example.redistest.entity.TestEntity;
import com.example.redistest.service.RedisService;
import com.example.redistest.service.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    // 레디스 주입
    private final RedisTemplate<String, Object> redisTemplate;
    private final ListOperations<String, Object> listOps;

    // 레디스를 사용하는 부분 -> TODO 프로젝트 환경에 맞게 코드 변경

    public void saveData(TestDto dataObject, String uuid, long time) {
        String json = JsonUtils.DTOtoRedisString(dataObject, uuid, time).replace("\"null\"", "null");
        System.out.println(json);
        // 데이터 넣기 예시
        listOps.leftPush(dataObject.getClass() + "example" + dataObject.getClass(), json);
        listOps.trim(dataObject.getClass() + "example" + dataObject.getClass(), 0, 99);
    }

    // 일부 데이터 가져오는 방식
    @SneakyThrows
    public List<TestEntity> getTestEntitiesById(String id) {
        List<Object> s = listOps.range("example", 0, -1);
        if (s.isEmpty()) {
            throw new NoSuchElementException();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<TestEntity> testEntities = new ArrayList<>();
        s.forEach(data -> {
            try {
                testEntities.add(ObjectMapper.readValue(data.toString(), TestEntity.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return testEntities.isEmpty() ? null : testEntities;
    }

    // 전체 데이터 가져오기
    public List<TestEntity> getDataAll() {
        List<TestEntity> testEntities = new ArrayList<>();

        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
        ScanOptions options = ScanOptions.scanOptions().match("example").build();

        Cursor<byte[]> cursor = connection.scan(options);

        while (cursor.hasNext()) {
            byte[] next = cursor.next();
            String matchedKey = new String(next, StandardCharsets.UTF_8);
            List<Object> s = listOps.range(matchedKey, 0, -1);
            ObjectMapper objectMapper = new ObjectMapper();
            s.forEach(data -> {
                try {
                    testEntities.add(objectMapper.readValue(data.toString(), TestEntity.class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return testEntities.isEmpty() ? null : testEntities;
    }
}
*/
