package com.example.redistest.entity;

import com.example.redistest.entity.Log;
import java.util.List;

public interface LogFacade {
    List<Log> findAllOrderByDateAtDesc();
    void removeAll();
}
