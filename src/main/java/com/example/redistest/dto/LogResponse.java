package com.example.redistest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class LogResponse {
    private Long id;
    private String message;
    private Date date;

}
