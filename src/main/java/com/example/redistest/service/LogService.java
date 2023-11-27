package com.example.redistest.service;

import com.example.redistest.dto.LogResponse;
import com.example.redistest.entity.LogFacade;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogFacade logFacade;

    @Cacheable(cacheNames = "searchAll",
            key = "#root.target + #root.methodName",
            sync = true,
            cacheManager = "rcm")
    public List<LogResponse> searchAll() {
        return logFacade.findAllOrderByDateAtDesc()
                .stream()
                .map(log -> new LogResponse(log.getId(),
                        log.getMessage(),
                        log.getDate()))
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "searchAll",
            allEntries = true,
            beforeInvocation = true,
            cacheManager = "rcm")
    @Transactional
    public void removeAll() {
        logFacade.removeAll();
    }
}
