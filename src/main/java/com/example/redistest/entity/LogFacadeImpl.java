package com.example.redistest.entity;

import com.example.redistest.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogFacadeImpl implements LogFacade {

    private final LogRepository logRepository;

    @Autowired
    public LogFacadeImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> findAllOrderByDateAtDesc() {
        return logRepository.findAllByOrderByDateDesc();
    }

    @Override
    public void removeAll() {
        logRepository.deleteAll();
    }
}
