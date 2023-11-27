package com.example.redistest.domain.entity;

import com.example.redistest.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class Members {

    // 캐싱 대상

    private List<Member> members = new ArrayList<>();

    public Members(List<Member> members) {
        this.members = members;
    }
}
