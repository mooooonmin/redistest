package com.example.redistest.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String name;
    private Integer age;

    // 생성자
    public MemberDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
