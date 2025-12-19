package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminSystemInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminSystemInfoServiceImpl implements AdminSystemInfoService {

    @Override
    public ResponseEntity<Map<String, Object>> getSystemInfo() {
        try {
            Map<String, Object> systemInfo = new HashMap<>();
            systemInfo.put("systemName", "JWT Security System");
            systemInfo.put("version", "1.0.0");
            systemInfo.put("currentTime", LocalDateTime.now());
            systemInfo.put("status", "Running");
            
            log.info("System info retrieved successfully");
            return ResponseEntity.ok(systemInfo);
        } catch (Exception e) {
            log.error("Failed to get system info: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 
