package com.example.redistest.controller;

import com.example.redistest.dto.LogResponse;
import com.example.redistest.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/log")
@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping
    public ResponseEntity<List<LogResponse>> getAll() {
        List<LogResponse> logs = logService.searchAll();
        return ResponseEntity.ok(logs);
    }

    @DeleteMapping
    public void deleteAll() {
        logService.removeAll();
    }
}
