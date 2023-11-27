package com.example.redistest.service.util;

import com.example.redistest.dto.TestDto;

public class JsonUtils {

    public static String DTOtoRedisString(TestDto object, String uuid, long time) {
        return String.format("json 형식으로 변환시킬 타입들 작성");
    }
}
