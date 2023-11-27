package com.example.redistest.domain;

public class MemberDto {
    private String name;
    private Integer age;

    // 기본 생성자
    public MemberDto() {
    }

    // 생성자
    public MemberDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // 이름 가져오기
    public String getName() {
        return name;
    }

    // 이름 설정
    public void setName(String name) {
        this.name = name;
    }

    // 나이 가져오기
    public Integer getAge() {
        return age;
    }

    // 나이 설정
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
