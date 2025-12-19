package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface AdminSystemInfoService {
    ResponseEntity<Map<String, Object>> getSystemInfo();
} 
