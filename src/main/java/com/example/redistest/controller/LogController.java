package com.example.redistest.controller;

import jakarta.persistence.Cacheable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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

    @Cacheable(cacheNames = "searchAll", key = "#root.target + #root.methodName", sync = true, cacheManager = "rcm")
    public List<LogResponse> searchAll() {
        return logFacade.findAllOrderByDateAtDesc()
                .stream()
                .map(LogResponse::new)
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "searchAll", allEntries = true, beforeInvocation = true, cacheManager = "rcm")
    @Transactional
    public void removeAll() {
        logFacade.removeAll();
    }
}
