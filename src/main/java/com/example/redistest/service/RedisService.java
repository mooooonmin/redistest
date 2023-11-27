package com.example.redistest.service;

import com.example.redistest.entity.TestEntity;

import java.util.List;

public interface RedisService {

    // TODO 진행하는 프로젝트의 환경에 따라 수정해주기
    List<TestEntity> getTestEntitiesById(String id);
    List<TestEntity> getDataAll();
    TestEntity getOne(String id, int index);
}
